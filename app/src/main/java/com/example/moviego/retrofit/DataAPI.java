package com.example.moviego.retrofit;

import com.example.moviego.ui.home.MovieItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataAPI {

    @GET("movies")
    Call<List<HomeService>> getMovies();

    @GET("tickets")
    Call<List<HomeService>> getTickets();

    @GET("seats")
    Call<List<HallService>> getSeats();

    @GET("seats/reserved")
    Call<List<ReservedSeatsService>> getReservedSeats(@Query("id_seansu") int id_seansu);

    @POST("book")
    Call<BookResponse> bookTickets(@Body BookResponse bookResponse);

//    void onSuccess(ArrayList<MovieItem> movieItems);
//    void onFailure();

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
