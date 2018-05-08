const express = require('express');
const morgan = require('morgan');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const app = express();
const plansRoutes = require('./api/routes/plans');
const membersRoutes = require('./api/routes/members');
const smsRoutes = require('./api/routes/sms');
const staffRoutes = require('./api/routes/staff');
const expensesRoutes = require('./api/routes/expenses');
const feePaymentRoutes = require('./api/routes/fee_payment') 
const attendanceRoutes = require('./api/routes/attendance')


// mongoose.connect(process.env.DB,{});
mongoose.connect('mongodb://nodeuser:nodepass@ds217349.mlab.com:17349/gtrack',{});
//database error handling
const connection = mongoose.connection;
connection.on('open',()=>{
	console.log(" database connected")
})
connection.on('error',()=>{
	console.log("error in connecting to database")
})
app.use(morgan('dev'));
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());
app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin','*');
    res.header('Access-Control-Allow-Headers','Origin, X-Requested-With, Content-Type, Accept, Authorization');
    if(req.method === 'OPTIONS') {
        res.header('Access-Control-Allow-Methods', 'GET, PUT, POST, PATCH, DELETE');
        return res.status(200).json({});
    }
    next();
});
app.use('/plans', plansRoutes);
app.use('/members', membersRoutes);
app.use('/sms', smsRoutes);
app.use('/staff',staffRoutes);
app.use('/expenses',expensesRoutes);
app.use('/fee',feePaymentRoutes)
app.use('/attendance',attendanceRoutes)
app.use((req, res, next) => {
    const err = new Error('Not Found');
    err.status = 404;
    next(err);
});

app.use((error,req, res, next) => {
    res.status(error.status || 500);
    res.json({
        error: {
            message: error.message
        }
    });
})

module.exports = app;
