const mongoose = require('mongoose');
const autoIncrement = require('mongoose-auto-increment');

const dotenv = require('dotenv').config() //loads .env file into process.env
const  connection = mongoose.createConnection(process.env.DB);
autoIncrement.initialize(connection);

const staffSchema = mongoose.Schema({
    name: {type: String,required:true},
    age: {type:Number,required:true},
    gender:{type: String,required:true},
    comments:{type: String,required:true},
    address:{type: String,required:true},
    phone:{type:Number,required:true},
    email:{type: String,required:true},
    aadhaar_number:{type:Number,required:true},
    PAN:{type: String,required:true},
})
staffSchema.plugin(autoIncrement.plugin, 'staff');

module.exports = mongoose.model('staff', staffSchema);
