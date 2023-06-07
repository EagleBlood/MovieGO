package com.example.moviego;

import com.example.moviego.retrofit.DataAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitFunction {

    private DataAPI dataAPI;

    private void retrofitFun(String endpoint){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/"+endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataAPI = retrofit.create(DataAPI.class);
    }

}
