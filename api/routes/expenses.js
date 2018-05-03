const express = require('express');
const router = express.Router();

const expensesController = require('../controllers/expenses');

router.get('/',expensesController.expenses_get);
router.post('/',expensesController.expenses_add);
router.patch('/:id',expensesController.expenses_update);
router.delete('/:id',expensesController.expenses_delete);

module.exports = router;