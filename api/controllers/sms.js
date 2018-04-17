const mongoose = require("mongoose");
const SMS = require("../models/sms");

exports.sms_sendsms = (req, res, next) => {
    console.log(req.body);
    // const sms = new SMS({
    //     _id: new mongoose.Types.ObjectId(),
    //     receivermobile: req.body.receivermobile,
    //     message: req.body.message
    // });
    // sms
    //     .save()
    //     .then(result => {
    //         console.log(result);
    //         res.status(201).json({
    //           message: "SMS send successfully"
    //         });
    //       })
    //     .catch(err => {
    //       console.log(err);
    //       res.status(500).json({
    //         error: err
    //       });
    //     });
};
