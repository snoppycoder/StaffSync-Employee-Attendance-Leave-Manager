const express = require('express');
const holidayRouter = express.Router();
const { PrismaClient } = require('@prisma/client');
const { identifyUser } = require('../utils/middleware');
const { parse } = require('date-fns');
const prisma = new PrismaClient();

holidayRouter.get('/', identifyUser, async (req, res) => {
  console.log(req.params);
  
  
    try {
        const holidays = await prisma.holiday.findMany();
        res.status(200).json(holidays);
    } catch (error) {
        console.error('Error fetching holidays:', error);
        res.status(500).json({ error: 'Internal server error' });
    }
}
);

// Endpoint to create a holiday
holidayRouter.post('/', async (req, res) => {
  try {
    const { title, startDate, endDate } = req.body;
    console.log(req.body);
    

    // Validate the incoming data
    if (!title || !startDate || !endDate) {
      return res.status(400).json({ error: 'Title, start date, and end date are required.' });
    }

    // Validate that startDate and endDate are proper Date objects
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
        createdBy: new Date(),
      },
    });
    console.log(req.body);
    

    // Respond with the created holiday
    res.status(201).json(holiday);
  } catch (error) {
    console.error('Error creating holiday:', error);
    res.status(500).json({ error: 'Could not create holiday.' });
  }
});


module.exports = holidayRouter;