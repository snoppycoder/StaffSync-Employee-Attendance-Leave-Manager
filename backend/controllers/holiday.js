const express = require('express');
const holidayRouter = express.Router();
const { PrismaClient } = require('@prisma/client');
const { identifyUser } = require('../utils/middleware');
const { parse } = require('date-fns');
const prisma = new PrismaClient();

holidayRouter.get('/', identifyUser, async (req, res) => {
    try {
        const holidays = await prisma.holiday.findMany({
            orderBy: {
                date: 'asc',
            },
        });
        res.status(200).json(holidays);
    } catch (error) {
        console.error('Error fetching holidays:', error);
        res.status(500).json({ error: 'Internal server error' });
    }
}
);

// Endpoint to create a holiday
holidayRouter.post('/', identifyUser, async (req, res) => {
  try {
    const { title, startDate, endDate } = req.body;

    if (!title || !startDate || !endDate) {
      return res.status(400).json({ error: 'Title, start date, and end date are required.' });
    }

    const start = new Date(startDate);
    const end = new Date(endDate);

    if (isNaN(start.getTime()) || isNaN(end.getTime())) {
      return res.status(400).json({ error: 'Invalid date format.' });
    }

    if (start > end) {
      return res.status(400).json({ error: 'Start date cannot be later than end date.' });
    }

    // Create a new holiday
    const holiday = await prisma.holiday.create({
      data: {
        title: title.trim(),
        startDate: start,
        endDate: end,
        createdBy: {
            connect: {
                id: req.user.id  // or just 2 if testing manually
            }
    }
      },
    });

    // Respond with the created holiday
    res.status(201).json(holiday);
  } catch (error) {
    console.error('Error creating holiday:', error);
    res.status(500).json({ error: 'Could not create holiday.' });
  }
});


module.exports = holidayRouter;