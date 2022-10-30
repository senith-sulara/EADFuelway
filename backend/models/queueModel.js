const mongoose = require("mongoose");
const Schema = mongoose.Schema;

let QueueModel = new Schema({
    stationName:{type: String},
    userMobile:{type: Number},
    vehicalType:{type: String},
    fuelType: {type: String},
    joined: {type:Boolean, default:false},
    leaveQueue : {type:String},
},
{
    timestamps: true,
}
);
QueueModel.index({ expireAfterSeconds: 60});
module.exports = mongoose.model("QueueModel", QueueModel);