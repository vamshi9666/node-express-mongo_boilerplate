const mongoose = require("mongoose");
const Plan = require("../models/plan");

exports.plans_get_all = (req, res, next) => {
    Plan.find()
    .select("name fees comments _id")
    .exec()
    .then(docs => {
        const response = {
            count: docs.length,
            plans: docs.map(doc => {
              return {
                _id: doc._id,
                name: doc.name,
                fees: doc.fees,
                comments: doc.comments,
                request: {
                  type: "GET",
                  url: "http://localhost:3001/plans/" + doc._id
                }
              };
            })
          };
          res.status(200).json(response);
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
};
exports.plans_create_plan = (req, res, next) => {
  console.log(req.body);
  const plan = new Plan({
    name: req.body.name,
    fees: req.body.fees,
    frequency: req.body.frequency,
    maxdiscount: req.body.maxdiscount,
    comments: req.body.comments,
    validuntil: req.body.validuntil,
    neverexpires: req.body.neverexpires
  })
  plan
    .save()
    .then(result => {
      if (res.session) {
        console.log(req.session);
      }
      req.session.plan = result
      console.log(result);
      res.status(201).json({
        message: "Created plan successfully",
        createdPlan: {
          _id: result._id,
          name: result.name,
          fees: result.fees,
          request: {
            type: 'GET',
            url: "http://localhost:3001/plans/" + result._id
          }
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
exports.plans_get_plan = (req, res, next) => {
    const id = req.params.planId;
    Plan.findById(id)
    .select('name fees comments _id')
    .exec()
    .then(doc => {
      console.log("From database", doc);
      if (doc) {
        res.status(200).json({
            plan: doc,
            request: {
                type: 'GET',
                url: 'http://localhost:3001/plans'
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

exports.plans_update_plan = (req, res, next) => {
    const planId = req.params.planId;
    const updateOps = {};
    for (const ops of req.body) {
      updateOps[ops.propName] = ops.value;
    }
    Plan.update({ _id: planId }, { $set: updateOps })
      .exec()
      .then(result => {
        res.status(200).json({
            message: 'Plan updated',
            request: {
                type: 'GET',
                url: 'http://localhost:3001/plans/' + id
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

exports.plans_delete_plan = (req, res, next) => {
    const planId = req.params.planId;
    Plan.remove({ _id: planId })
    .exec()
    .then(result => {
        res.status(200).json({
            message: 'Plan deleted',
            request: {
                type: 'POST',
                url: 'http://localhost:3001/plans',
                body: { name: 'String', fees: 'Number' , comments: 'String' }
            },
            result:result
        });
      })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
};
