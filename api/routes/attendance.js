const express = require('express');
const router = express.Router();

const attendanceController = require('../controllers/attendance');

router.post('/',attendanceController.attendance_add);
router.get('/',attendanceController.attendance_get);
router.patch('/:id',attendanceController.attendance_update);
router.delete('/:id',attendanceController.attendance_remove);

module.exports = router;