const mongoose = require('mongoose');

const SMSMessaageTemplateSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    smstype: { type: String, required: true },
    template: { type: String, required: true }
});

const SMSVendorSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    vendorcode: { type: String, required: true },
    countryid: { type: Number, required: true },
    login: { type: String, required: true },
    password: { type: String, required: true },
    accountno: { type: String, required: true }
});

const SMSVendorStatusSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    vendorid: { type: Number, required: true },
    statuscode: { type: String, required: true },
    statusdescription: { type: String, required: true },
    issuccess: { type: Boolean, required: true },
    retryrequired: { type: Boolean, required: true }
});

const SMSLogSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    mobile: { type: String, required: true },
    message: { type: String, required: true },
    smstype: { type: String},
    internalrefno: { type: String},
    vendorid: { type: String},
    vendorrefno: { type: String},
    vendorstatusid: { type: Number },
    noofretries: { type: String },
    requestpayload: { type: String},
    responsepayload: { type: String},
    createdby: { type: String},
    createddate: { type: String}
});

module.exports = mongoose.model('SMSMessaageTemplate', SMSMessaageTemplateSchema);
module.exports = mongoose.model('SMSVendor', SMSVendorSchema);
module.exports = mongoose.model('SMSVendorStatus', SMSVendorStatusSchema);
module.exports = mongoose.model('SMSLog', SMSLogSchema);