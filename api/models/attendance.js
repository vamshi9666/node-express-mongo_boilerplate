const mongoose = require('mongoose');
const autoIncrement = require('mongoose-auto-increment');

const  connection = mongoose.createConnection("mlab link");
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
