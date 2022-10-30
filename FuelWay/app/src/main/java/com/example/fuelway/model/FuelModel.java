/**
 * Fuel station model
 *
 */
package com.example.fuelway.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FuelModel {

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("fuelStationName")
    @Expose
    private String fuelStationName;

    @SerializedName("ownerNIC")
    @Expose
    private String ownerNIC;

    @SerializedName("stationLocationURL")
    @Expose
    private String stationLocationURL;

    @SerializedName("petrol")
    @Expose
    private boolean petrol;

    @SerializedName("diesel")
    @Expose
    private boolean diesel;

    @SerializedName("petrolAT")
    @Expose
    private String petrolAT;

    @SerializedName("dieselAT")
    @Expose
    private String dieselAT;

    @SerializedName("petrolL")
    @Expose
    private int petrolL;

    @SerializedName("dieselL")
    @Expose
    private int dieselL;

    @SerializedName("petrolFT")
    @Expose
    private String petrolFT;

    @SerializedName("dieselFT")
    @Expose
    private String dieselFT;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFuelStationName() {
        return fuelStationName;
    }

    public void setFuelStationName(String fuelStationName) {
        this.fuelStationName = fuelStationName;
    }

    public String getOwnerNIC() {
        return ownerNIC;
    }

    public void setOwnerNIC(String ownerNIC) {
        this.ownerNIC = ownerNIC;
    }

    public String getStationLocationURL() {
        return stationLocationURL;
    }

    public void setStationLocationURL(String stationLocationURL) {
        this.stationLocationURL = stationLocationURL;
    }

    public boolean isPetrol() {
        return petrol;
    }

    public void setPetrol(boolean petrol) {
        this.petrol = petrol;
    }

    public boolean isDiesel() {
        return diesel;
    }

    public void setDiesel(boolean diesel) {
        this.diesel = diesel;
    }

    public String getPetrolAT() {
        return petrolAT;
    }

    public void setPetrolAT(String petrolAT) {
        this.petrolAT = petrolAT;
    }

    public String getDieselAT() {
        return dieselAT;
    }

    public void setDieselAT(String dieselAT) {
        this.dieselAT = dieselAT;
    }

    public int getPetrolL() {
        return petrolL;
    }

    public void setPetrolL(int petrolL) {
        this.petrolL = petrolL;
    }

    public int getDieselL() {
        return dieselL;
    }

    public void setDieselL(int dieselL) {
        this.dieselL = dieselL;
    }

    public String getPetrolFT() {
        return petrolFT;
    }

    public void setPetrolFT(String petrolFT) {
        this.petrolFT = petrolFT;
    }

    public String getDieselFT() {
        return dieselFT;
    }

    public void setDieselFT(String dieselFT) {
        this.dieselFT = dieselFT;
    }

    @Override
    public String toString() {
        return "FuelModel{" +
                "_id='" + _id + '\'' +
                ", fuelStationName='" + fuelStationName + '\'' +
                ", ownerNIC='" + ownerNIC + '\'' +
                ", stationLocationURL='" + stationLocationURL + '\'' +
                ", petrol=" + petrol +
                ", diesel=" + diesel +
                ", petrolAT='" + petrolAT + '\'' +
                ", dieselAT='" + dieselAT + '\'' +
                ", petrolL=" + petrolL +
                ", dieselL=" + dieselL +
                ", petrolFT='" + petrolFT + '\'' +
                ", dieselFT='" + dieselFT + '\'' +
                '}';
    }
}
