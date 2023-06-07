package com.example.moviego.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFunction<T> {

    private DataAPI dataAPI;

    public RetrofitFunction() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataAPI = retrofit.create(DataAPI.class);
    }

    public DataAPI dataAPI() {
        return dataAPI;
    }

//    public void getData(Call<T> call, final Callback<T> callback) {
//        call.enqueue(new Callback<T>() {
//            @Override
//            public void onResponse(Call<T> call, Response<T> response) {
//                if (response.isSuccessful()) {
//                    callback.onResponse(call, response);
//                } else {
//                    callback.onFailure(call, new Throwable("Response unsuccessful. Status code: " + response.code()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<T> call, Throwable t) {
//                callback.onFailure(call, t);
//            }
//        });
//    }
//
//    public void sendData(Call<T> call, final Callback<T> callback) {
//        call.enqueue(new Callback<T>() {
//            @Override
//            public void onResponse(Call<T> call, Response<T> response) {
//                if (response.isSuccessful()) {
//                    callback.onResponse(call, response);
//                } else {
//                    callback.onFailure(call, new Throwable("Response unsuccessful. Status code: " + response.code()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<T> call, Throwable t) {
//                callback.onFailure(call, t);
//            }
//        });
//    }
}

