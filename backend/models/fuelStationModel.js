const mongoose = require("mongoose");
const Schema = mongoose.Schema;

let fuelStationSchema = new Schema({
    fuelStationName:{type: String},
    ownerNIC:{type:String},
    stationLocationURL:{type: String},
    petrol: {type:Boolean,default:false},
    diesel: {type:Boolean,default:false},
    petrolAT: {type : String},
    dieselAT: {type : String},
    petrolL: {type : Number,default:0},
    dieselL: {type : Number,default:0},
    petrolFT: {type : String},
    dieselFT: {type : String}
},
);

module.exports = mongoose.model("fuelStation", fuelStationSchema);