const express = require('express');
const notificationRouter = express.Router();
const { PrismaClient } = require('@prisma/client');
const { identifyUser, rbacMiddleware } = require('../utils/middleware');
const logger = require('../utils/logger');
const prisma = new PrismaClient();

notificationRouter.get('/', identifyUser, async (req, res) => {
    try {
        const notifications = await prisma.notification.findMany({
        where: {
            userId: req.user.id,
        },
        orderBy: {
            createdAt: 'desc',
        },
        });
    
        res.status(200).json(notifications);
    } catch (error) {
        logger.error('Error fetching notifications:', error);
        res.status(500).json({ error: 'Could not retrieve notifications' });
    }
});

notificationRouter.get('/:id', identifyUser, async (req, res) => {
    try {
        const notification = await prisma.notification.findUnique({
            where: {
                id: parseInt(req.params.id),
            },
        });

        if (!notification) {
            return res.status(404).json({ error: 'Notification not found' });
        }
        if (notification.userId !== req.user.id) {
            return res.status(403).json({ error: 'Forbidden' });
        }
        res.status(200).json(notification);
    } catch (error) {
        logger.error('Error fetching notification:', error);
        res.status(500).json({ error: 'Could not retrieve notification' });
    }
});

    //Leave Requester Notification
    notificationRouter.post('/request', identifyUser, async (req, res) => {
        const { leaveRequestId, startDate, endDate } = req.body;
    const userId = req.user.id;

    try {
        const notification = await prisma.notification.create({
            data: {
                userId,
                leaveRequestId,
                message: `Your leave request from ${new Date(startDate).toDateString()} to ${new Date(endDate).toDateString()} has been submitted.`,
            },
        });

        res.status(201).json(notification);
    } catch (error) {
        logger.error('Error creating leave request notification:', error);
        res.status(500).json({ error: 'Could not create leave request notification' });
    }
});



//Manager Approval Notification
notificationRouter.post('/approve', identifyUser, async (req, res) => {
    const { leaveRequestId, userId } = req.body;
    const managerName = req.user.username || 'Manager';

    try {
        const notification = await prisma.notification.create({
            data: {
                userId,
                leaveRequestId,
                message: `Your leave request has been approved by ${managerName}.`,
            },
        });

        res.status(201).json(notification);
    } catch (error) {
        logger.error('Error creating approval notification:', error);
        res.status(500).json({ error: 'Could not create approval notification' });
    }
});



notificationRouter.delete('/', identifyUser, async (req, res) => {
    const userId = req.user.id;

    try {
        const deletedNotifications = await prisma.notification.deleteMany({
            where: {
                userId: userId,
            },
        });

        if (deletedNotifications.count === 0) {
            return res.status(404).json({ error: 'No notifications found to delete' });
        }

        res.status(200).json({ message: `${deletedNotifications.count} notification(s) deleted` });
    } catch (error) {
        logger.error('Error deleting notifications:', error);
        res.status(500).json({ error: 'Could not delete notifications' });
    }
});


module.exports = notificationRouter;