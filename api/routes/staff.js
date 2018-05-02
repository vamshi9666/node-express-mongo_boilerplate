const express = require('express');
const router = express.Router();

const staffController = require('../controllers/staff');

router.get('/:id',staffController.staff_get_by_id)
router.get('/',staffController.staff_get_all)
router.post('/',staffController.staff_add);
router.patch('/:id',staffController.staff_update);
router.delete('/:id',staffController.staff_remove)
module.exports = router 