package com.example.moviego.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataAPI {

    @GET("movies")
    Call<List<HomeService>> getMovies();

    @GET("tickets")
    Call<List<TicketService>> getTickets(@Query("id_uzyt") int id_uzyt);

    @GET("seats")
    Call<List<HallService>> getSeats();

    @GET("seats/reserved")
    Call<List<ReservedSeatsService>> getReservedSeats(@Query("id_seansu") int id_seansu);

    @POST("book")
    Call<BookResponse> bookTickets(@Body BookResponse bookResponse);

    @GET("login")
    Call<Boolean> checkLogin(@Query("login") String login, @Query("password") String password);

    @GET("users/{username}")
    Call<UserService> getUserDetails(@Path("username") String username);

    @Headers("Content-Type: application/json")
    @POST("register")
    Call<RegistrationResponse> addUser(@Body RegistrationResponse registrationResponse);


}
