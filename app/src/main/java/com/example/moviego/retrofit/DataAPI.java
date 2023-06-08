package com.example.moviego.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataAPI {

    @GET("movies")
    Call<List<HomeService>> getMovies();

//    @GET("shoutbox/messages?last=12")
//    Call<List<ShoutboxData>> getShoutboxData();
//
//    @POST("shoutbox/message")
//    Call<DataApiResponse> postJson(@Body DataApiResponse dataApiResponse);
//
//    @PUT("shoutbox/message/{id}")
//    Call<DataApiResponse> editPost(@Path("id") String id, @Body DataApiResponse dataApiResponse);
//
//    @DELETE("shoutbox/message/{id}")
//    Call<ShoutboxData> deleteItem(@Path("id") String id);

}
