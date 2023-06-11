package com.example.moviego.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFunction {

    private static DataAPI dataAPI;

    public RetrofitFunction() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataAPI = retrofit.create(DataAPI.class);
    }


    public DataAPI dataAPI() {
        return dataAPI;
    }


}

