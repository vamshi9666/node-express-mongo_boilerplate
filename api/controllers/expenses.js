const Expenses = require('../models/expenses');

exports.expenses_add = (req, res, next) => {
  const expenses = new Expenses({
    fromDate: req.body.fromDate,
    toDate: req.body.toDate,
    type: req.body.type,
    amount: req.body.amount,
    comments: req.body.comments
  })
  expenses.save()
    .then(result => {
      const response = {
        fromDate: result.fromDate,
        toDate: result.toDate,
        type: result.type,
        amount: result.amount,
        comments: result.comments
      }
      res.status(200).json({
        message: "expenses created !",
        created_expenses: response
      })
    })
    .catch(err => {
      res.status(200).json(err)
    })
}
exports.expenses_update =(req,res,next)=>{
    const id = req.params.id;
    const updates ={};
    for (const oupdateps of req.body) {
        updates[update.propName] = update.value;
      }
    Expenses.update({_id:id},{$set:updates})
    .exec()
    .then(result=>{
        res.status(200).json({
            message:"updated expenses !",
            updated_expenses:result
        })
    })
    .catch(err=>{
        res.status(200).json(err)
    })
}
exports.expenses_delete = (req, res, next) => {
  const id = req.params.id;
  Expenses.remove({
      _id: id
    })
    .exec()
    .then(result => {
      if (result.n === '1') {
        res.status(200).json({
          message: "expenses removed successfully !"
        })
      } else {
        res.status(300).json({
          message: "something went wrong in deleting expenses .please try again "
        })
      }
    })

}
exports.expenses_get = (req,res,next)=>{
    Expenses.find()
    .select('_id fromDate toDate amount comments type')
    .then(docs=>{
        res.status(200).json({
            message:"fetched expenses data ! ",
            result:docs
        })
    })
    .catch(err=>{
        res.status(200).json(err)
    })

}
