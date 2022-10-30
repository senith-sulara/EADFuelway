/**
 * Retrofit Station Service Class
 *
 */

package com.example.fuelway.Service;


import com.example.fuelway.model.FuelModel;
import com.example.fuelway.model.UserClass;
import com.example.fuelway.model.station;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface StationService {
    //https://www.youtube.com/watch?v=FwO142JrdBo

    //Get All Fuel Station
    @GET("getAllFuelStations/")
    Call<List<FuelModel>> getAllStations();

    //Get Fuel Station for the specific Owner
    @GET("get/{ownerNIC}")
    Call<List<FuelModel>> getStation(@Path("ownerNIC") String ownerNIC);

    //Add New Fuel Station when Owner Registering
    @POST("insertFuelS/")
    Call<FuelModel> addFuelStation(@Body FuelModel fuelModel);

    //Owner Login
    @POST("signin/")
    Call<FuelModel> stationLogin(@Body FuelModel fuelModel);

    //Owner update station data
    @PUT("{id}")
    Call<Void> updateFuelStation(@Path("id") String _id, @Body FuelModel fuelModel);

}

