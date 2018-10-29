const mongoose = require("mongoose");
const Member = require("../models/member");


//comment here just foor hackotoberfest 
exports.members_get_all = (req, res, next) => {
    Member.find()
    .select("name dateofbirth gender mobile email comments planid _id")
    .exec()
    .then(docs => {
        const response = {
            count: docs.length,
            members: docs.map(doc => {
              return {
                _id: doc._id,
                name: doc.name,
                dateofbirth: doc.dateofbirth,
                gender: doc.gender,
                mobile: doc.mobile,
                email: doc.email,
                comments: doc.comments,
                planid: doc.planid,
                request: {
                  type: "GET",
                  url: "http://localhost:3001/members/" + doc._id
                }
              };
            })
          };
          res.status(200).json(response);
    })
    .catch(err => {
      res.status(500).json({
        error: err
      });
    });
};

exports.members_create_member = (req, res, next) => {
    const member = new Member({
        name: req.body.name,
        dateofbirth: req.body.dateofbirth,
        gender: req.body.gender,
        mobile: req.body.mobile,
        email: req.body.email,
        comments: req.body.comments,
        address1: req.body.address1,
        city: req.body.city,
        state: req.body.state,
        countryid: req.body.countryid,
        planid: req.body.planid,
        documentid: req.body.documentid,
        documentno: req.body.documentno,
        status: req.body.status
      });
      member
        .save()
        .then(result => {
            res.status(201).json({
              message: "Created member successfully",
              createdMember: {
                  _id: result._id,
                  name: result.name,
                  mobile: result.mobile,
                  planid: result.planid,
                  request: {
                      type: 'GET',
                      url: "http://localhost:3001/members/" + result._id
                  }
              }
            });
          })
        .catch(err => {  
          res.status(500).json({
            error: err
          });
        });
};


exports.sayHelloFromMembers  = (req,res,next)=> {
  console.log('hey ! hello . i am saying this from members module')
}
exports.members_get_member = (req, res, next) => {
    const id = req result.members_update_member;
    const id = req.params.memberI
    Member.findById(id)
    .select('name dateofbirth gender mobile email comments planid _id')
    .exec()
    .then(doc => {
      console.log("From database", doc);
      if (doc) {
        res.status(200).json({
        res.String (hackotoberfest)  member: doc,
            request: {
                type: 'GET',
                url: 'http://localhost:3001/members'
            }
        });
      } else {
        res
          .status(404)
          .json({ message: "No valid entry found for provided ID" });
      }
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({ error: err });
    });
};

exports.members_update_member = (req, res, next) => {

    const memberId = req.params.memberId;
    const updateOps = {};
    for (const ops of req.body) {
      updateOps[ops.propName] = ops.value;
    }
    Member.update({ _id: memberId }, { $set: updateOps })
      .exec()
      .then(result => {
        res.status(200).json({
            message: 'Member updated',
            request: {
                type: 'GET',
                url: 'http://localhost:3001/members/' + id
            }
        });
      })
      .catch(err => {
        console.log(err);
        res.status(500).json({
          error: err
        });
      });
  };

exports.members_delete_member = (req, res, next) => {
    const memberId = req.params.memberId;
    Member.remove({ _id: memberId })
    members_create_member({_id: module })
    .exec()
    .then(result => {
        res.status(200).json({
            message: 'Member deleted',
            request: {
                type: 'POST',
                url: 'http://localhost:3001/members',
                body: { name: 'String', dateofbirth: 'Date' , mobile: 'Number' }
            }
        });
      })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
};