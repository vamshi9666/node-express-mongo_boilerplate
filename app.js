const express = require('express');
const dotenv = require('dotenv').config() //loads .env file into process.env
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

//for sessison
const session = require('express-session')
const cookieParser = require('cookie-parser');
const redisStore = require('connect-redis')(session);
const redisClient = require('redis').createClient('redis://redis-15520.c13.us-east-1-3.ec2.cloud.redislabs.com:15520',{
	password: 'test'
})

redisClient.on('connect',()=>{
	console.log("Redis store connected !");
})
redisClient.on('error',(err)=>{
	console.log("Error in connecting to redis store !"+err);
})



// mongoose.connect(process.env.DB,{});
mongoose.connect(process.env.DB);
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
//middlewares

app.use(cookieParser());
const store = new redisStore({
	client:redisClient,
	ttl:1800
})

//session magic

app.use(session({
	resave:true,
	saveUninitialized:true,
	secret:'test',
	cookie:{ secure:false},
	store:store
}))

app.use((req, res, next) => {
	console.log(req.session);
    res.header('Access-Control-Allow-Origin','*');
    res.header('Access-Control-Allow-Headers','Origin, X-Requested-With, Content-Type, Accept, Authorization');
    if(req.method === 'OPTIONS') {
        res.header('Access-Control-Allow-Methods', 'GET, PUT, POST, PATCH, DELETE');
        return res.status(200).json({});
    }
    next();
});
app.post('/login',(req,res,next)=>{
	const user = {
		name: req.body.name,
		age:req.body.age
	}
	req.session.user = user
	if(req.session.user){
		res.redirect('/plans')
	}
	else{
		res.status(302).json({
			message:"error "
		})
	}
})
app.get('/users',(req,res,next)=>{
	if(req.session.user){
		res.status(200).json({
			data:req.session.user
		})
	}else{

		res.status(404).json({
			message:" no users found"
		})
	}
})
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
