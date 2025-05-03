const userRouter = require('express').Router();
const { errorHandler, identifyUser } = require('../utils/middleware');
const { error } = require('../utils/logger');
const bcrypt = require('bcrypt');
const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();

userRouter.get('/', identifyUser, async (req, res) => {
    try {
        const users = await prisma.user.findMany();
        res.json(users);
    } catch (error) {
        res.status(500).json({ error: 'Could not retrieve users' });
    }
});

module.exports = userRouter;