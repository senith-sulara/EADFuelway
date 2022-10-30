const express = require('express');
const mongoose = require('mongoose');
const dotenv = require('dotenv');
const cors = require('cors');
const StationRoute = require('./controllers/fuelStationController');
const QueueRoute = require('./controllers/fuelQueueController');

dotenv.config();
const app = express();
app.use(cors());
app.use(express.urlencoded({extended: true}));
app.use(express.json());

const PORT = process.env.PORT || 9292;
const MONGODB_URI = process.env.MONGODB_URI;

mongoose.connect(MONGODB_URI,{
        useNewUrlParser:true,
        useUnifiedTopology:true,
},(error) =>{
    if(error){
        console.log('DataBase ERROR: ',error.message);
    }
});

mongoose.connection.once('open', () => {
    console.log('Database Synced');
});

app.use('/stationR', StationRoute);
app.use('/queueR', QueueRoute);

app.listen(PORT, () =>{
    console.log(`Server is running on PORT ${PORT}`);
});