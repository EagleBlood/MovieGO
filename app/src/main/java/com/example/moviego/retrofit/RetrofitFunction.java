package com.example.moviego.retrofit;

import com.example.moviego.ui.home.MovieItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

