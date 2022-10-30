const path = require('path');
const express = require('express');
const FuelStations = require('../models/fuelStationModel');
const jwt = require("jsonwebtoken");
const Router = express.Router();
const config = require('../utils/config.js')

Router.post(
  '/addFuelStation',
    async (req, res) => {
      try {
        let fuelStations = new FuelStations({
          fuelStationName: req.body.fuelStationName,  
          ownerNIC: req.body.ownerNIC,
          stationLocationURL: req.body.stationLocationURL, 
          petrol: req.body.petrol ? true : false,
          diesel: req.body.diesel ? true : false,
          petrolAT: req.body.petrolAT,
          dieselAT: req.body.dieselAT, 
          petrolL: req.body.petrolL,
          dieselL: req.body.dieselL,
          petrolFT: req.body.petrolFT,
          dieselFT: req.body.dieselFT,

        });
      await fuelStations.save();
      res.send('Fuel Satation details uploaded successfully.');
      } catch (error) {
        res.status(400).send('Error while uploading Fuel Station details. Try again later.');
      }
    },
    (error, req, res, next) => {
      if (error) {
        res.status(500).send(error.message);
      }
    }
);

Router.post('/insertFuelS', async (request, response) => {
  const fuelStation = new FuelStations(request.body);
  fuelStation.save((error, fuelStation) => {
    if (error) {
      response.status(500).json({ error: error.message });
    }
    else {
      response.status(200).
        json({
          success: true,
          fuelStation: fuelStation
        });
    }
  });
}
);

Router.post('/signin',(req,res)=>{
  FuelStations.findOne({fuelStationName:req.body.fuelStationName,ownerNIC:req.body.ownerNIC},(err,result)=>{
      if (err) return res.status(500).json({ msg: err });
      if (result === null) {
        return res.status(403).json("Station name is incorrect");
      }
      if (result.ownerNIC === req.body.ownerNIC) {
        let token = jwt.sign({ ownerNIC: req.body.ownerNIC }, config.key, {});
        res.json({
          // token: token,
          // msg: "success",
          ownerNIC: req.body.ownerNIC,
          fuelStationName:req.body.fuelStationName
        });
      } else {
        res.status(403).json("NIC is incorrect");
      }
  })
})

// get fuelStations details

Router.get('/getAllFuelStations', async (req, res) => {
  try {
    const stations = await FuelStations.find({});
    const sortedByCreationDate = stations.sort(
      (a, b) => b.createdAt - a.createdAt
    );
    res.send(sortedByCreationDate);
  } catch (error) {
    res.status(400).send('Error while getting FuelStations. Try again later.');
  }
});

Router.get('/get/:ownerNIC', async (req, res) => {
  try {
    const stations = await FuelStations.find({ownerNIC : req.params.ownerNIC});
    const sortedByCreationDate = stations.sort(
      (a, b) => b.createdAt - a.createdAt
    );
    res.status(200).send(sortedByCreationDate);
  } catch (error) {
    res.status(400).send('Error while getting FuelStations. Try again later.');
  }
});

Router.get('/getone/:ownerNIC', async(request,response) => {
  try {
    FuelStations.find({ownerNIC : request.params.ownerNIC}, (error, data) => {
          if (error) {
              response.status(500).json({error: error.message});
          } else {
              response.status(200).json({
                  success: true,
                  fuelStation: data,
              })
          }
      })
  } catch (e) {
      console.log(e);
  }
});

//Update
Router.put("/:id", async (req, res) => {
  try {
    let fuelStations = await FuelStations.findById(req.params.id);
    const data = {
      petrol: req.body.petrol ? true:false,
      diesel: req.body.diesel ? true:false,
      petrolAT: req.body.petrolAT || fuelStations.petrolAT,
      dieselAT: req.body.dieselAT || fuelStations.dieselAT,
      petrolL: req.body.petrolL || fuelStations.petrolL,
      dieselL: req.body.dieselL || fuelStations.dieselL,
      // petrolFT: req.body.petrolFT || fuelStations.petrolFT,
      // dieselFT: req.body.dieselFT || fuelStations.dieselFT

    };
    fuelStations = await FuelStations.findByIdAndUpdate(req.params.id, data, { new: true });
    res.json(fuelStations).status(200);
  } catch (e) {
    res.status(400).json({ msg: e.message, success: false });
  }
});

//Update
Router.put("/:ownerNIC", async (req, res) => {
  try {
    let fuelStations = await FuelStations.find({"ownerNIC" : req.params.ownerNIC});
    const data = {
      petrol: req.body.petrol ? true : false || fuelStations.petrol ? true : false,
      diesel: req.body.diesel ? true : false || fuelStations.diesel ? true : false,
      petrolAT: req.body.petrolAT || fuelStations.petrolAT,
      dieselAT: req.body.dieselAT || fuelStations.dieselAT,
      petrolL: req.body.petrolL || fuelStations.petrolL,
      dieselL: req.body.dieselL || fuelStations.dieselL,
      petrolFT: req.body.petrolFT || fuelStations.petrolFT,
      dieselFT: req.body.dieselFT || fuelStations.dieselFT

    };
    fuelStations = await FuelStations.findOneAndUpdate({"ownerNIC" : req.params.ownerNIC}, data, { new: true });
    res.json(fuelStations);
  } catch (e) {
    res.status(400).json({ msg: e.message, success: false });
  }
});

//Delete
Router.delete("/:id", async (req, res) => {
  try {
    // Find fuelStations by id
    const fuelStations = await FuelStations.findById(req.params.id);
    if (!fuelStations) throw Error('No file found');
    const removed = await fuelStations.remove();
    if (!removed)
         throw Error('Something went wrong while trying to delete the file');
    res.json(fuelStations);
  } catch (e) {
    res.status(400).json({ msg: e.message, success: false });
  }
});


module.exports = Router;