package com.example.moviego;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviego.retrofit.DataAPI;
import com.example.moviego.retrofit.HallService;
import com.example.moviego.retrofit.HomeService;
import com.example.moviego.retrofit.RetrofitFunction;
import com.example.moviego.ui.home.MovieItem;
import com.example.moviego.ui.movie.Hall;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreen extends AppCompatActivity {

    private DataAPI dataAPI;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        retrofitFun();
        setHall();
        setMovie();

        intent = new Intent(this, DrawerAndBottomNavActivity.class);

    }


    private void retrofitFun(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataAPI = retrofit.create(DataAPI.class);
    }

    private void setHall() {
        Call<List<HallService>> hallServiceCall = dataAPI.getSeats();

        hallServiceCall.enqueue(new Callback<List<HallService>>() {
            @Override
            public void onResponse(Call<List<HallService>> call, Response<List<HallService>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Błąd: " + response.code());
                    return;
                }

                List<HallService> hallServices = response.body();
                List<Hall> halls = new ArrayList<>();


                if (hallServices != null) {
                    for (HallService hallService : hallServices){

                        int seatId = hallService.getSeatId();
                        int row = hallService.getRow();
                        int armchair = hallService.getArmchair();

                        halls.add(new Hall(seatId, row, armchair));
                    }
                }

                MyApp.getInstance().setHalls(halls);
            }

            @Override
            public void onFailure(Call<List<HallService>> call, Throwable t) {
                System.out.println("Błąd: " + t.getMessage());
            }
        });
    }

    private void setMovie() {
        Call<List<HomeService>> homeServiceCall = dataAPI.getMovies();


        homeServiceCall.enqueue(new Callback<List<HomeService>>() {
            @Override
            public void onResponse(Call<List<HomeService>> call, Response<List<HomeService>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Błąd: " + response.code());
                    return;
                }

                List<HomeService> homeServices = response.body();
                ArrayList<MovieItem> movieItems = new ArrayList<>();

                if (homeServices != null) {
                    for (HomeService homeService : homeServices) {
                        int id_filmu = homeService.getId_filmu();
                        String tytul = homeService.getTytul();
                        int czas_trwania = homeService.getCzas_trwania();
                        double ocena = homeService.getOcena();
                        String opis = homeService.getOpis();
                        String okladka = homeService.getOkladka();
                        double cena = homeService.getCena();
                        String nazwa_gatunku = homeService.getNazwa_gatunku();
                        String data = homeService.getData();
                        String pora_emisji = homeService.getPora_emisja();
                        int id_seansu = homeService.getId_seansu();

                        movieItems.add(new MovieItem(id_filmu, tytul, czas_trwania, ocena, opis, okladka, cena, nazwa_gatunku, data, pora_emisji, id_seansu));
                    }
                }

                MyApp.getInstance().setMovieItems(movieItems);

                new Handler().postDelayed(() -> {

                    startActivity(intent);
                    finish();
                }, 1000);
            }

            @Override
            public void onFailure(Call<List<HomeService>> call, Throwable t) {
                System.out.println("Błąd: " + t.getMessage());
            }
        });
    }

}