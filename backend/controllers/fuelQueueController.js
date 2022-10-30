const path = require('path');
const express = require('express');
const FuelQueues = require('../models/queueModel');

const Router = express.Router();

Router.post(
  '/addfuelQueue',
    async (req, res) => {
      try {
        let fuelQueue = new FuelQueues({
          stationName: req.body.stationName, 
          userMobile: req.body.userMobile,  
          vehicalType: req.body.vehicalType, 
          fuelType: req.body.fuelType,
          joined: req.body.joined ? true : false, 
          leaveQueue: req.body.leaveQueue,
        });
      await fuelQueue.save();
      res.send('Fuel Queue details uploaded successfully.');
      } catch (error) {
        res.status(400).send('Error while uploading Fuel Queue. Try again later.');
      }
    },
    (error, req, res, next) => {
      if (error) {
        res.status(500).send(error.message);
      }
    }
);

// get fuelQueue details

Router.get('/getAllFuelQueue', async (req, res) => {
  try {
    const queues = await FuelQueues.find({});
    const sortedByCreationDate = queues.sort(
      (a, b) => b.createdAt - a.createdAt
    );
    res.send(sortedByCreationDate);
  } catch (error) {
    res.status(400).send('Error while getting FuelQueues. Try again later.');
  }
});
//Get All vehicals count in queue
Router.get('/getAllQueue/:stationName', async (req, res) => {
  try {
    const que =await FuelQueues.count({stationName: req.params.stationName, joined: true})
        res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
});
//Get All vehicals count in petrol queue
Router.get('/getPetQueue/:stationName', async (req, res) => {
    try {

    const que =await FuelQueues.count({stationName: req.params.stationName ,fuelType: "Petrol", joined: true})
        res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
  });
//Get All vehicals count in diesel queue

  Router.get('/getDieQueue/:stationName', async (req, res) => {
    try {
      const que =await FuelQueues.count({stationName: req.params.stationName ,fuelType: "Diesel", joined: true})
      res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
  });

//Get All car count in queue

  Router.get('/getCarQueue/:stationName', async (req, res) => {
    try {
    const que =await FuelQueues.count({stationName: req.params.stationName ,vehicalType: "Car", joined: true})
        res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
  });

//Get All Bike count in queue

  Router.get('/getBikeQueue/:stationName', async (req, res) => {
    try {

    const que =await FuelQueues.count({stationName: req.params.stationName ,vehicalType: "Bike", joined: true})
        res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
  });

//Get All lorry count in petrol queue

  Router.get('/getLorryQueue/:stationName', async (req, res) => {
    try {

    const que =await FuelQueues.count({stationName: req.params.stationName ,vehicalType: "Lorry", joined: true})
        res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
  });

  //Get All bus count in petrol queue

  Router.get('/getBusQueue/:stationName', async (req, res) => {
    try {

    const que =await FuelQueues.count({stationName: req.params.stationName ,vehicalType: "Bus", joined: true})
        res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
  });

  //Get All van count in petrol queue

  Router.get('/getVanQueue/:stationName', async (req, res) => {
    try {

    const que =await FuelQueues.count({stationName: req.params.stationName ,vehicalType: "Van", joined: true})
        res.json(que);

    } catch (error) {
      res.status(400).send('Error while getting Queue. Try again later.');
    }
  });

//Update
Router.put("/:userMobile", async (req, res) => {
  try {
    let fuelQueue = await FuelQueues.find({"userMobile" : req.params.userMobile});
    const data = {
      joined: req.body.joined ? true : false || fuelQueue.joined ? true : false,
      leaveQueue: req.body.leaveQueue || fuelQueue.leaveQueue,
    };
    fuelQueue = await FuelQueues.findOneAndUpdate({"userMobile" :req.params.userMobile}, data, { new: true });
    res.json(fuelQueue);
  } catch (e) {
    res.status(400).json({ msg: e.message, success: false });
  }
});


module.exports = Router;