/**
 * Retrofit Client Api Class
 * Api for Station Management
 */


package com.example.fuelway.Api;

import com.example.fuelway.Service.StationService;

import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiStation {
    public static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

//Assign the base url

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ead-fuel.herokuapp.com/stationR/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }


    public static StationService getStationService(){
        StationService stationService = getRetrofit().create(StationService.class);
        return stationService;
    }
}
