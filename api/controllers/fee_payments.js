const FeePayment = require('../models/fee_payments');

exports.fee_payment_add = (req,res,next)=>{
    const fee_payment = new FeePayment({
        date:req.body.date,
        plan:req.body.plan,
        due_amount:req.body.due_amount,
        paid_amount:req.body.paid_amount,
        comments:req.body.comments
    })
    fee_payment.save()
    // .exec()
    .then(result=>{
        res.status(200).json({
            message:"paymen ccreated successfully !",
            created_result :result
        })
    })
    .catch(err=>{
        console.log(err);
        res.status(505).json({
            message:"something went wrong in server ! .Please try again",
            error : err

        })
    })
}
exports.fee_payment_get = (req,res,next)=>{
    FeePayment.find({})
    .select('_id  plan date due_amount paid_amount')
    .exec()
    .then(result=>{
        res.status(200).json({
            message:"fetched data from server !",
            data:result
        })
    })
    .catch(err=>{
        console.log(err);
        res.status(505).json({
            message:"something went wrong in server ! .Please try again",
            error :err

        })
    })
}
exports.fee_payment_update= (req,res,next)=>{
    const id = req.params.id;
    const updates ={};
    for (const update of req.body){
        updates[update.propName] = update.value;
        }
    FeePayment.update({_id:id},{$set:updates})
    .exec()
    .then(resullt=>{
        res.status(200).json({
            message:"updated fee payment !",
            result:result
        })
    })
    .catch(err=>{
        console.log(err);
        res.status(505).json({
            message:"something went wrong in server ! .Please try again",
            error :err
        })
    })
}
exports.fee_payment_delete = (req,res,next)=>{
    const id = req.params.id;
    FeePayment.remove({_id:id})
    .exec()
    .then(result=>{
        if(result.n=='1'){
        res.status(200).json({
            message:"expenses removed successfully !"

        })
    }
    else{
        res.status(300).json({
            message:"something went wrong in deleting fee records .please try again "
        })
    }
    })
    .catch(err=>{
        console.log(err);
        res.status(505).json({
            message:"something went wrong in server ! .Please try again",
            error :err
        })
    })
}
