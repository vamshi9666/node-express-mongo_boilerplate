const mongoose = require('mongoose');

const memberSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    name: { type: String, required: true },
    dateofbirth: { type: Date, required: true },
    gender: { type: String, required: true },
    mobile: { type: Number, required: true },
    email: { 
        type: String,  
        required: true, 
        unique: true, 
        match: /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/
    },
    comments: { type: String, required: true },
    address1: { type: String, required: true },
    address2: { type: String},
    city: { type: String, required: true },
    state: { type: String, required: true },
    zip: { type: String },
    countryid: { type: Number, required: true },
    contactpreference: { type: String },
    planid: { type: Number, required: true },
    documentid: { type: Number, required: true },
    documentno: { type: String, required: true },
    status: { type: Number, required: true },
});

module.exports = mongoose.model('Member', memberSchema);