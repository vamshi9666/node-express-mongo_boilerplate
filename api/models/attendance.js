const mongoose = require('mongoose');
const autoIncrement = require('mongoose-auto-increment');

const dotenv = require('dotenv').config() //loads .env file into process.env
const  connection = mongoose.createConnection(process.env.DB);
autoIncrement.initialize(connection);

const attendanceSchema = mongoose.Schema({
	_id:{
		type:Number
	},
	name:{type:String,required:true},
	present:{type:Boolean,required:true},
	half_day:{type:Boolean,required:true},
	comments:{type:String}
})

attendanceSchema.plugin(autoIncrement.plugin, 'attendance');
module.exports = mongoose.model('attendance',attendanceSchema);
