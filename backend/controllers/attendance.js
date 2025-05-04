const express = require('express');
const { PrismaClient } = require('@prisma/client');
const { getTokenFrom, identifyUser, rbacMiddleware } = require('../utils/middleware');
const logger = require('../utils/logger');

const prisma = new PrismaClient();
const attendanceRouter = express.Router();

// POST /api/attendance/check-in - Record check-in (all authenticated users)
attendanceRouter.post('/check-in', identifyUser, async (req, res, next) => {
  try {
    // Check for active check-in (no check-out)
    const activeAttendance = await prisma.attendance.findFirst({
      where: {
        userId: req.user.id,
        checkOut: null,
      },
    });

    if (activeAttendance) {
      return res.status(400).json({ error: 'You must check out before checking in again' });
    }

    // Create attendance record
    const attendance = await prisma.attendance.create({
      data: {
        userId: req.user.id,
        checkIn: new Date(),
        date: new Date(),
      },
      include: { user: { select: { email: true,  profile: { select: { fullName: true } } } } },
    });

    

    res.status(201).json({
      message: 'Checked in successfully',
      attendance: {
        id: attendance.id,
        checkInTime: attendance.checkInTime,
      },
    });
  } catch (error) {
    logger.error('Check-in error:', error);
    next(error);
  } finally {
    await prisma.$disconnect();
  }
});

// POST /api/attendance/check-out - Record check-out (all authenticated users)
attendanceRouter.post('/check-out', identifyUser, async (req, res, next) => {
  try {
    // Find active check-in
    const activeAttendance = await prisma.attendance.findFirst({
      where: {
        userId: req.user.id,
        checkOut: null,
      },
      include: { user: { select: { email: true, profile: { select: { fullName: true } } } } },
    });

    if (!activeAttendance) {
      return res.status(400).json({ error: 'No active check-in found' });
    }

    // Update attendance record
    const attendance = await prisma.attendance.update({
      where: { id: activeAttendance.id },
      data: { checkOut: new Date() },
      select: {
        id: true,
        checkIn: true,
        checkOut: true,
      },
    });

    res.status(200).json({
      message: 'Checked out successfully',
      attendance,
    });
  } catch (error) {
    logger.error('Check-out error:', error);
    if (error.code === 'P2025') {
      return res.status(400).json({ error: 'No active check-in found' });
    }
    next(error);
  } finally {
    await prisma.$disconnect();
  }
});

// GET /api/attendance - List attendance history
attendanceRouter.get('/',identifyUser, async (req, res, next) => {
  try {
    const { userId } = req.query;

    // Managers/Admins can view others' attendance
    let whereClause = { userId: req.user.id };
    whereClause = { userId: parseInt(userId) };

    const attendanceRecords = await prisma.attendance.findMany({
      where: whereClause,
      orderBy: { checkInTime: 'desc' },
      select: {
        id: true,
        checkIn: true,
        checkOut: true,
        createdAt: true,
        user: { select: { username: true, profile: { select: { fullName: true } } } },
      },
    });

    res.status(200).json(attendanceRecords);
  } catch (error) {
    logger.error('Attendance fetch error:', error);
    next(error);
  } finally {
    await prisma.$disconnect();
  }
});

module.exports = attendanceRouter;