const Attendance = require('../models/attendance');

exports.attendance_update = (req, res, next) => {
  const id = req.params.id;
  const updates = {};
  for (const update of req.body) {
    updates[update.propName] = update.value
  }
  Attendance.update({
      _id: id
    }, {
      $set: updates
    })
    .exec()
    .then(result => {
      res.status(200).json({
        message: "updated expenses !",
        updated_expenses: result
      })
    })
    .catch(err => {
      res.status(200).json(err)
    })
}

exports.attendance_add = (req, res, next) => {
  const attendance = new Attendance({
    name: req.body.name,
    present: req.body.present,
    half_day: req.body.half_day,
    comments: req.body.comments
  })
  attendance.save()
    .then(result => {
      res.status(200).json({
        message: "attendance record created !",
        created_result: result
      })
    })
    .catch(err => {
      res.status(505).json({
        message: "error in creating record ! please try again .",
        error: err
      })
    })
}
exports.attendance_remove = (req, res, next) => {
  const id = req.params.id;
  Attendance.remove({
      _id: id
    })
    .exec()
    .then(result => {
      res.status(200).json({
        message: "attendance record deleted successfully !",
        data: result
      })
    })
    .catch(err => {
      res.status(505).json({
        message: "error in deleting record .please try again",
        error: err
      })
    })
}
exports.attendance_get = (req, res, next) => {
  Attendance.find({})
    // .select('_id name present half_day comments')
    .exec()
    .then(result => {
      res.status(200).json({
        message: "fecthed data from server .",
        data: result
      })
    })
    .catch(err => {
      res.status(500).json({
        message: "error in fetching data from serevr . please try again",
        error: err
      })
    })
}
