const express = require('express');
const router = express.Router();

const feeController = require('../controllers/fee_payments');


router.get('/',feeController.fee_payment_get);
router.post('/',feeController.fee_payment_add);
router.patch('/:id',feeController.fee_payment_update);
router.delete('/:id',feeController.fee_payment_delete);

module.exports = router;