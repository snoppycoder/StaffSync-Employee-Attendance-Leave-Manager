const express = require('express');
const leaveRequestRouter = express.Router();
const { PrismaClient } = require('@prisma/client');
const { identifyUser } = require('../utils/middleware');
const { error } = require('../utils/logger');
const prisma = new PrismaClient();

leaveRequestRouter.get('/', identifyUser, async(req, res)=> {
    try{
        const leaveRequests = await prisma.leaveRequests.findMany({
            include:{
                user:true,
                leaveType:true,
            }
        });
        res.status(200).json(leaveRequests);    
    }catch(e){
        console.error('Error fetching leave requests:', e);
        res.status(500).json({ error: 'Internal server error' });
    }
}
);

leaveRequestRouter.get('/:id', identifyUser, async(req,res) => {
    const { id } = req.params;
    try{
        const foundLeaveRequest = await prisma.leaveRequests.findUnique({
            where: { id: parseInt(id) },
            include:{
                user:true,
                leaveType:true,
            }
        })

        if (!foundLeaveRequest){
            return(res.status(404).json({error: "No such leave request found"}))
        }
        res.json(foundLeaveRequest);
    }catch(e){
        console.error('Error fetching leave request:', e);
        res.status(500).json({ error: 'Internal server error' });
    }
});

leaveRequestRouter.post('/', identifyUser, async (req, res) => {
    try{
        const { type, startDate, endDate, reason } = req.body;

    // Validate input
    if (!type || !startDate || !endDate || !reason) {
      return res.status(400).json({ error: 'All fields are required' });
    }
    if (!['SICK', 'VACATION', 'PERSONAL'].includes(type)) {
      return res.status(400).json({ error: 'Invalid leave type: Must be SICK, VACATION, or PERSONAL' });
    }
    const start = new Date(startDate);
    const end = new Date(endDate);
    if (isNaN(start.getTime()) || isNaN(end.getTime())) {
      return res.status(400).json({ error: 'Invalid start or end date' });
    }
    if (start > end) {
      return res.status(400).json({ error: 'Start date must be before end date' });
    }
    if (reason.length < 10) {
      return res.status(400).json({ error: 'Reason must be at least 10 characters long' });
    }

    // Create leave request
    const leaveRequest = await prisma.leaveRequest.create({
      data: {
        userId: req.user.id,
        type,
        startDate: start,
        endDate: end,
        reason,
        status: 'PENDING',
      },
      include: { user: { select: { email: true, profile: { select: { fullName: true } } } } },
    });
          res.status(201).json({
            message: 'Leave request submitted successfully',
            leaveRequest: {
              id: leaveRequest.id,
              type: leaveRequest.type,
              startDate: leaveRequest.startDate,
              endDate: leaveRequest.endDate,
              status: leaveRequest.status,
            },
          });
    }catch(e){
        console.error('Error creating leave request:', e);
        res.status(500).json({ error: 'Internal server error' });
    }
}
);

module.exports = leaveRequestRouter;