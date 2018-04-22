const mongoose = require("mongoose");
const SMS = require("../models/sms");

exports.sms_sendsms = (req, res, next) => {
    console.log(req.body);
    const sms = new SMS({
        _id: new mongoose.Types.ObjectId(),
        mobile: req.body.mobile,
        message: req.body.message
    });
    sms
        .save()
        .then(result => {
            console.log(result);
            
            request.get('https://api.textlocal.in/send?apikey=d4KexqsYhXM-V87HtBfATp0yqYfK2xot5nFQRfxpL9&sender=TXTLCL&numbers=919652927213&message=Hi', function (error, response, body) {
              console.log('error:', error); // Print the error if one occurred
              console.log('statusCode:', response && response.statusCode); // Print the response status code if a response was received
              console.log('body:', body); // Print the HTML for the Google homepage.
              res.status(201).json({
                message: "SMS send successfully",
                result: response.body
              });
            });
          })
        .catch(err => {
          console.log(err);
          res.status(500).json({
            error: err
          });
        });
};
