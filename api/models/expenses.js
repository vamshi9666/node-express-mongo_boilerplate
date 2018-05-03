const mongoose = require('mongoose');

const expenseSchema = mongoose.Schema({
    _id:mongoose.Schema.Types.ObjectId,
    fromDate:{
        type:String,
        required:true
    },
    toDate:{
        type:String,
        required:true
    },
    type:{
        type:String,
        required:true
    },
    amount:{
        type:Number,
        required:true
    },
    comments:{
        type:String
    }

})

module.exports = mongoose.model('expenses',expenseSchema);