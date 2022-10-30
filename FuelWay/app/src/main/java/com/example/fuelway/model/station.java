package com.example.fuelway.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class station {
    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("fuelStationName")
    @Expose
    private String fuelStationName;

    @SerializedName("stationEmail")
    @Expose
    private String stationEmail;

    @SerializedName("stationAddress")
    @Expose
    private String stationAddress;

    @SerializedName("stationLocationURL")
    @Expose
    private String stationLocationURL;

    @SerializedName("isPetrolAvailable")
    @Expose
    private boolean isPetrolAvailable;

    @SerializedName("isDieselAvailable")
    @Expose
    private boolean isDieselAvailable;

    @SerializedName("fuelUpdatedDateTime")
    @Expose
    private String fuelUpdatedDateTime;

    @SerializedName("phoneNumber")
    @Expose
    private int phoneNumber;

    @SerializedName("petrolLit")
    @Expose
    private int petrolLit;

    @SerializedName("dieselLit")
    @Expose
    private int dieselLit;


    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getFuelStationName() {
        return fuelStationName;
    }

    public void setFuelStationName(String fuelStationName) {
        this.fuelStationName = fuelStationName;
    }

    public String getStationEmail() {
        return stationEmail;
    }

    public void setStationEmail(String stationEmail) {
        this.stationEmail = stationEmail;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public String getStationLocationURL() {
        return stationLocationURL;
    }

    public void setStationLocationURL(String stationLocationURL) {
        this.stationLocationURL = stationLocationURL;
    }

    public boolean isPetrolAvailable() {
        return isPetrolAvailable;
    }

    public void setPetrolAvailable(boolean petrolAvailable) {
        isPetrolAvailable = petrolAvailable;
    }

    public boolean isDieselAvailable() {
        return isDieselAvailable;
    }

    public void setDieselAvailable(boolean dieselAvailable) {
        isDieselAvailable = dieselAvailable;
    }

    public String getFuelUpdatedDateTime() {
        return fuelUpdatedDateTime;
    }

    public void setFuelUpdatedDateTime(String fuelUpdatedDateTime) {
        this.fuelUpdatedDateTime = fuelUpdatedDateTime;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPetrolLit() {
        return petrolLit;
    }

    public void setPetrolLit(int petrolLit) {
        this.petrolLit = petrolLit;
    }

    public int getDieselLit() {
        return dieselLit;
    }

    public void setDieselLit(int dieselLit) {
        this.dieselLit = dieselLit;
    }

    @Override
    public String toString() {
        return "station{" +
                "_id='" + _id + '\'' +
                ", fuelStationName='" + fuelStationName + '\'' +
                ", stationEmail='" + stationEmail + '\'' +
                ", stationAddress='" + stationAddress + '\'' +
                ", stationLocationURL='" + stationLocationURL + '\'' +
                ", isPetrolAvailable=" + isPetrolAvailable +
                ", isDieselAvailable=" + isDieselAvailable +
                ", fuelUpdatedDateTime='" + fuelUpdatedDateTime + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", petrolLit=" + petrolLit +
                ", dieselLit=" + dieselLit +
                '}';
    }
}
