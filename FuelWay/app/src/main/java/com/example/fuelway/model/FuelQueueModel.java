/**
 * Fuel Queue Model class
 */
package com.example.fuelway.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FuelQueueModel {

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("stationName")
    @Expose
    private String stationName;

    @SerializedName("userMobile")
    @Expose
    private int userMobile;

    @SerializedName("vehicalType")
    @Expose
    private String vehicalType;

    @SerializedName("fuelType")
    @Expose
    private String fuelType;

    @SerializedName("joined")
    @Expose
    private boolean joined=false;

    @SerializedName("leaveQueue")
    @Expose
    private String leaveQueue;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(int userMobile) {
        this.userMobile = userMobile;
    }

    public String getVehicalType() {
        return vehicalType;
    }

    public void setVehicalType(String vehicalType) {
        this.vehicalType = vehicalType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }

    public String getLeaveQueue() {
        return leaveQueue;
    }

    public void setLeaveQueue(String leaveQueue) {
        this.leaveQueue = leaveQueue;
    }

    @Override
    public String toString() {
        return "FuelQueueModel{" +
                "_id='" + _id + '\'' +
                ", stationName='" + stationName + '\'' +
                ", userMobile=" + userMobile +
                ", vehicalType='" + vehicalType + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", joined=" + joined +
                ", leaveQueue='" + leaveQueue + '\'' +
                '}';
    }
}
