const mongoose = require('mongoose');

const autoIncrement = require('mongoose-auto-increment');

const dotenv = require('dotenv').config() //loads .env file into process.env
const  connection = mongoose.createConnection(process.env.DB);
autoIncrement.initialize(connection);

const planSchema = mongoose.Schema({
    name: { type: String, required: true },
    fees: { type: Number, required: true },
    frequency: { type: Number, required: true },
    maxdiscount: { type: Number, required: true },
    comments: { type: String, required: true },
    validuntil: Date,
    neverexpires: { type: Boolean, required: true }
});
planSchema.plugin(autoIncrement.plugin, 'Plan');

module.exports = mongoose.model('Plan', planSchema);
