package com.example.moviego;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {
    @GET("/movies")
    Call<String> getMovies();
}
