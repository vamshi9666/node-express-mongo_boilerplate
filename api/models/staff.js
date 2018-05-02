const mongoose = require('mongoose');

const staffSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
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
module.exports = mongoose.model('staff', staffSchema);
