const mongoose = require('mongoose');
const autoIncrement = require('mongoose-auto-increment');
var connection = mongoose.createConnection("mongodb://nodeuser:nodepass@ds217349.mlab.com:17349/gtrack");

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
