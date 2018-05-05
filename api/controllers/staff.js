const mongoose = require('mongoose');
const Staff = require('../models/staff');

exports.staff_get_all = (req,res,next)=>{
    Staff.find()
    .select(" _idname age gender comments address phone email aadhar_number PAN")
    .exec()
    .then(docs=>{
        const response = {
            count : docs.length,
            staff :docs.map(doc=>{
                return{
                    _id:doc._id,
                    name : doc.name,
                    age :doc.age,
                    gender:doc.gender,
                    comments :doc.comments,
                    address:doc.address,
                    phone:doc.phone,
                    email:doc.email,
                    aadhaar_number:doc.aadhaar_number,
                    PAN:doc.PAN,

                }
            })
        }
        res.status(200).json(response)
    })
    .catch(err=>{
        res.status(404).json({
            message:"went wrong in fetching all staff ! ",
            error : err
        })
    })

}
exports.staff_get_by_id= (req,res,next)=>{
    const id = req.params.id;
    Staff.findById(id)
    .select("name age gender comments address phone email aadhar_number PAN")
    .then(result=>{
    if(result){
        res.status(200).json(result)
    }
    else{
        res.status(404).json({
            message : "no valid staff are recorded in database.  please try with another id "
        })
    }
    })

}
exports.staff_add = (req,res,next)=>{
    const staff = new Staff({
        name: req.body.name,
        age: req.body.age,
        gender:req.body.gender,
        comments:req.body.comments,
        address:req.body.address,
        phone:req.body.phone,
        email:req.body.email,
        aadhaar_number:req.body.aadhaar_number,
        PAN:req.body.PAN,
    });
    staff.save()
    .then(result =>{

        console.log(result);
        res.status(200).json({
            message: "employee created !",
            created_employee: result
        })
    })
    .catch(err=>{
        console.log(err);
        res.status(301).json({
            message:"error in saving !  please try again.",
            error :err
        })
    })
}
exports.staff_update = (req,res,next)=>{
    const id = req.params.id;
    const updateOps = {};
    for (const ops of req.body ) {
      updateOps[ops.propName] = ops.value;
    }
    Staff.update({_id:id},{$set:updateOps})
    .then(result=>{
        res.status(200).json({
            message:"employee updated !",
            updated_employee: result
        })
    })
    .catch(err=>{
        res.status(300).json({
            message: " error in updating employee !",
            error : err
        })
    })
}
exports.staff_remove = (req,res,next)=>{
    const id = req.params.id;
    Staff.remove({_id:id})
    .exec()
    .then(docs=>{
        res.status(200).json({
            message:"employee removed successfully !",
            updated_employees:docs
        })
    })
    .catch(err=>{
    console.log("error in removing employee !");
    res.status(300).json({
        message:"error in removing employee .\n try again !",
        error:err
    })
    })
}
