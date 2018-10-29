const express = require('express');
const router = express.Router();
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
// const bcrypt = require('bcrypt')
const jwt = require('jsonwebtoken')
const auth = require('./api/middlewares/auth')
//for sessison
const session = require('express-session')
const cookieParser = require('cookie-parser');
const redisStore = require('connect-redis')(session);
const redisClient = require('redis').createClient(process.env.REDIS,{
	password: 'test'
})

redisClient.on('connect',()=>{
	console.log("Redis store connected !");
})
redisClient.on('error',(err)=>{
	console.log("Error in connecting to redis store !"+err);
})

//signup and login
const User = require('./api/models/user')
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
    res.header('Access-Control-Allow-Origin','*');
    res.header('Access-Control-Allow-Headers','Origin, X-Requested-With, Content-Type, Accept, Authorization');
    if(req.method === 'OPTIONS') {
        res.header('Access-Control-Allow-Methods', 'GET, PUT, POST, PATCH, DELETE');
        return res.status(200).json({});
    }
    next();
});
// app.post('/signup', (req, res, next) => {
//   bcrypt.hash(req.body.password, 10, (err, hash) => {
//     if (err) {
//       console.log(err);
//       res.status(500).json({
//         message: " error in signup controller ",
//         error: err
//       })
//     } else {
//       const user = new User({
//         userName: req.body.username,
//         password: hash
//       })
// 			user.save()
// 					.then(result=>{
// 						res.status(200).json({
// 							message:" user created !",
// 							data: result
// 						})
// 					})
//     }
//   })
// })
// app.post('/login', (req, res, next) => {
//   User.find({
//       userName: req.body.username
//     })
//     .then(result => {
//       console.log(result)
//       if (result.length < 1) {
//         return res.status(301).json({
//           message: "Error in authentication"
//         })
//       }
//       bcrypt.compare(req.body.password, result[0].password, (err, doc) => {
//         if (err) {
//           console.log(err);
//           return res.status(201).json({
//             message: " error in authentication",
//             erroe: err
//           })
//         }
//         if (doc) {
//           const token = jwt.sign({
//             userName: req.body.username
//           }, process.env.JWT_KEY, {
//             expiresIn: "1h"
//           })
// 					 return res.status(200).json({
//             message: "user authenticated successfully !",
// 						session:req.session,
// 						token:token
//           })
//         }
//       })
//     })
//     .catch(err => {
//       console.log(err)
//       res.status(201).json({
//         message: " error in authentication",
//         error: err
//       })
//     })
// });
app.use('/plans',auth , plansRoutes);
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

module.exports = app
