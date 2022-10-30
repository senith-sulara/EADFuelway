/**
 * Retrofit Client Api Class
 * Api for Queue Management
 */


package com.example.fuelway.Api;

import com.example.fuelway.Service.FuelQueueService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiQueue {
    public static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

//Assign the base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ead-fuel.herokuapp.com/queueR/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }


    public static FuelQueueService getQueueService(){
        FuelQueueService fuelQueueService = getRetrofit().create(FuelQueueService.class);
        return fuelQueueService;
    }
}
