const mongoose = require('mongoose');

const planSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    name: { type: String, required: true },
    fees: { type: Number, required: true },
    frequency: { type: Number, required: true },
    maxdiscount: { type: Number, required: true },
    comments: { type: String, required: true },
    validuntil: Date,
    neverexpires: { type: Boolean, required: true }
});

module.exports = mongoose.model('Plan', planSchema);