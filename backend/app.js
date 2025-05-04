const express = require('express');
const app = express();
const userRouter = require('./controllers/user');
const loginRouter = require('./controllers/login');
const signupRouter = require('./controllers/signup');
const {getTokenFrom} = require('./utils/middleware')
const logoutRouter = require('./controllers/logout');

app.use(express.json())
app.use(getTokenFrom)

app.get('/', (req, rep) => {
    rep.send('<h1> Hello World </h1>')
  })

app.use('/api/users', userRouter);
app.use('/api/login', loginRouter);
app.use('/api/signup', signupRouter);
app.use('/api/logout', logoutRouter);

module.exports = app;