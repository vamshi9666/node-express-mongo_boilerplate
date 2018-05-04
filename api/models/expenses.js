const mongoose = require('mongoose');
const AutoIncrement = require('mongoose-sequence')(mongoose);

const expenseSchema = mongoose.Schema({
    _id:{type:Number},
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
expenseSchema.plugin(AutoIncrement, {inc_field: '_id'})

module.exports = mongoose.model('expenses',expenseSchema);