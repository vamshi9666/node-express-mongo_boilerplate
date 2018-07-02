const mongoose = require('mongoose');

const autoIncrement = require('mongoose-auto-increment');

const dotenv = require('dotenv').config() //loads .env file into process.env
const  connection = mongoose.createConnection(process.env.DB);
autoIncrement.initialize(connection);

const fee_paymentSchema = mongoose.Schema({
    _id:{type:Number},
    date:{required:true,type:Date},
    plan:{type:String,required:true},
    due_amount:{type:Number,required:true},
    paid_amount:{type:Number,required:true},
    comments:{type:String}
}
)

fee_paymentSchema.plugin(autoIncrement.plugin, 'fee_payment');
module.exports = mongoose.model('fee-payment', fee_paymentSchema);
