/**
 * Retrofit Queue Service Class
 *
 */
package com.example.fuelway.Service;

import com.example.fuelway.model.FuelQueueModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FuelQueueService {
    //Get All vehicle queue by station
    @GET("getAllQueue/{stationName}")
    Call<Object> getAllQueue(@Path("stationName") String stationName);

    //Get petrol queue by station
    @GET("getPetQueue/{stationName}")
    Call<Object> getPetQueue(@Path("stationName") String stationName);

    //Get Diesel queue by station
    @GET("getDieQueue/{stationName}")
    Call<Object> getDieQueue(@Path("stationName") String stationName);

    //Get Car queue by station
    @GET("getCarQueue/{stationName}")
    Call<Object> getCarQueue(@Path("stationName") String stationName);

    //Get Bike queue by station
    @GET("getBikeQueue/{stationName}")
    Call<Object> getBikeQueue(@Path("stationName") String stationName);

    //Get Lorry queue by station
    @GET("getLorryQueue/{stationName}")
    Call<Object> getLorryQueue(@Path("stationName") String stationName);

    //Get Van queue by station
    @GET("getVanQueue/{stationName}")
    Call<Object> getVanQueue(@Path("stationName") String stationName);

    //Add vehicle to queue
    @POST("addfuelQueue/")
    Call<Void> createQueue(@Body FuelQueueModel fuelQueueModel);

    //Update vehicle leave from queue
    @PUT("{userMobile}")
    Call<FuelQueueModel> updateQueue(@Path("userMobile") int userMobile, @Body FuelQueueModel fuelQueueModel);
}
