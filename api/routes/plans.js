const express = require('express');
const router = express.Router();
const PlansController = require('../controllers/plans');

router.get('/', PlansController.plans_get_all);

router.post('/', PlansController.plans_create_plan);

router.get('/:planId', PlansController.plans_get_plan);

router.patch('/:planId', PlansController.plans_update_plan);

router.delete('/:planId', PlansController.plans_delete_plan);

module.exports = router;