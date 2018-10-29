const express = require('express');
const router = express.Router();
const MembersController = require('../controllers/members');

router.get('/', MembersController.sayHelloFromMembers);

router.post('/', MembersController.members_create_member);

router.get('/:memberId', MembersController.members_get_member);

router.patch('/:memberId', MembersController.members_update_member);

router.delete('/:memberId', MembersController.members_delete_member);

module.exports = router;