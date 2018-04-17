const express = require('express');
const router = express.Router();
const SMSController = require('../controllers/sms');

router.post('/sendsms', SMSController.sms_sendsms);

module.exports = router;