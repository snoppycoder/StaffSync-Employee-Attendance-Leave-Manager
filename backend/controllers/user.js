
const userRouter = require('express').Router();
const { errorHandler, identifyUser } = require('../utils/middleware');
const { error } = require('../utils/logger');
const bcrypt = require('bcrypt');
const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();

userRouter.get('/', async (req, res) => {
    try {
        const users = await prisma.user.findMany();
        res.json(users);
    } catch (error) {
        res.status(500).json({ error: 'Could not retrieve users' });
    }
});
userRouter.get('/employee', async (req, res) => {
  try {
    const foundUsers = await prisma.user.findMany({
      where: { role: 'EMPLOYEE' },
      include: {
        profile: {
          select: {
            fullName: true,
        }
      }}
    });

    if (!foundUsers || foundUsers.length === 0) {
      return res.status(404).send({ error: "No such user found" });
    }
    const mergedUsers = foundUsers.map(user => ({
      id: user.id,
      username: user.username,
      email: user.email,
      role: user.role,
      fullName: user.profile.fullName, // Add fullName to the response
    }));

    res.send(mergedUsers);
  } catch (e) {
    console.error('Error fetching employees:', e);
    res.status(500).send({ error: 'Could not retrieve users' });
  }
});

userRouter.get('/manager', async (req, res) => {
  try {
    const foundUsers = await prisma.user.findMany({
      where: { role: 'MANAGER' },
      include: {
        profile: {
          select: {
            fullName: true,
        }
      }}
    });

    if (!foundUsers || foundUsers.length === 0) {
      return res.status(404).send({ error: "No such user found" });
    }
    const mergedUsers = foundUsers.map(user => ({
      id: user.id,
      username: user.username,
      email: user.email,
      role: user.role,
      fullName: user.profile.fullName, // Add fullName to the response
    }));

    res.send(mergedUsers);
  } catch (e) {
    console.error('Error fetching employees:', e);
    res.status(500).send({ error: 'Could not retrieve users' });
  }
});


userRouter.get('/:id', async (req, res) => {
  const { id } = req.params;

  try {
    const foundUser = await prisma.user.findUnique({
      where: { id: parseInt(id) },
      include: {
        profile: {
          select: {
            fullName: true,
            designation: true,
          },
        },
        leaveBalances: {
          select: {
            type: true,
            balance: true,
          },
        },
        leaveRequests:{
          select:{
            status: true
          },
        },
        
      },
    });

    if (!foundUser) {
      return res.status(404).json({ error: 'User not found' });
    }

    const mergedUser = {
    
      username: foundUser.username,
      email: foundUser.email,
      fullName: foundUser.profile?.fullName ?? null, // safe access
      designation: foundUser.profile?.designation ?? null, 
      status: foundUser.leaveRequests,
      leaveBalances: foundUser.leaveBalances[0]?.balance ?? null, 

    };
    console.log('Leave Requests:', foundUser.leaveRequests);


    res.send(mergedUser);
    console.log('Merged user:', mergedUser);
  } catch (e) {
    console.error('Error fetching user:', e);
    res.status(500).json({ error: 'Could not retrieve user' });
  }
});




// for the change your password functionality
userRouter.patch('/:id', identifyUser, async (req, res, next) => {
    try {
      const { id } = req.params;
      const { oldPassword, newPassword} = req.body;
      const userId = req.user.id;
      const saltRounds = 10;
  
      const targetId = parseInt(id, 10);
      if (isNaN(targetId)) {
        return res.status(400).json({ error: 'Invalid user ID' });
      }
  

      if (userId !== targetId) {
        return res.status(403).json({ error: 'Unauthorized: You can only update your own password' });
      }
  
      // Validate password
      if (!oldPassword || !newPassword) {
        return res.status(400).json({ error: 'New password and confirm password are required' });
      }
      const user = await prisma.user.findUnique({
        where: { id: targetId },
    });
    if (newPassword.length < 6) {
        return res.status(400).json({ error: 'Password must be at least 6 characters long' });
    }


        const passwordMatch = await bcrypt.compare(oldPassword, user.password);
        if (!passwordMatch) {
        return res.status(401).json({ error: 'Incorrect old password' });
        }
  
      // Update password
      const updatedUser = await prisma.user.update({
        where: { id: targetId },
        data: {
          password: await bcrypt.hash(newPassword, saltRounds),
        },
        select: {
          id: true,
          username: true,
          email: true,
          role: true,
        },
      });
      res.status(200).json({
        id: updatedUser.id,
        username: updatedUser.username,
        email: updatedUser.email,
        role: updatedUser.role,
        name: updatedUser.name,
      });
    } catch (error) {
      console.error('Update user password error:', error);
      if (error.code === 'P2025') {
        return res.status(404).json({ error: 'User not found' });
      }
      if (error.code === 'P2002') {
        return res.status(400).json({ error: 'Username or email already exists' });
      }
      next(error); // Pass to errorHandler
    }
  });

  userRouter.delete('/:id', identifyUser, async(req, res) => {
    const { id } = req.params;
    const userId = req.user.id;
    const targetId = parseInt(id, 10);
    if (isNaN(targetId)) {
        return res.status(400).json({ error: 'Invalid user ID' });
    }
    if (userId !== targetId) {
        return res.status(403).json({ error: 'Unauthorized: You can only delete your own account' });
    }
    try {
        const deletedUser = await prisma.user.delete({
            where: { id: targetId },
        });
        res.status(200).json({ message: 'User deleted successfully', user: deletedUser });
    } catch (error) {
        console.error('Delete user error:', error);
        if (error.code === 'P2025') {
            return res.status(404).json({ error: 'User not found' });
        }
        next(error); // Pass to errorHandler
    }

});

  module.exports = userRouter;