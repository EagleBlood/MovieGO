package com.example.moviego;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        RetrofitFunction retrofitFunction = new RetrofitFunction();
        DataAPI dataAPI = retrofitFunction.dataAPI();

        List<Hall> HALLS = MyApp.getInstance().getHALLS();
        List<MovieItem> MOVIE_ITEM = MyApp.getInstance().getMOVIES();


        if(MOVIE_ITEM.size() == 0 || HALLS.size() == 0) {
            setHall(dataAPI);
            setMovie(dataAPI);
        } else {
            nextActivity();
        }

        intent = new Intent(this, DrawerAndBottomNavActivity.class);

    }

    private void setHall(DataAPI dataAPI) {



        Call<List<HallService>> hallServiceCall = dataAPI.getSeats();

        hallServiceCall.enqueue(new Callback<List<HallService>>() {
            @Override
            public void onResponse(@NonNull Call<List<HallService>> call, @NonNull Response<List<HallService>> response) {
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

                MyApp.getInstance().setHALLS(halls);
            }

            @Override
            public void onFailure(@NonNull Call<List<HallService>> call, @NonNull Throwable t) {
                System.out.println("Błąd: " + t.getMessage());
            }
        });
    }

    private void setMovie(DataAPI dataAPI) {


        Call<List<HomeService>> homeServiceCall = dataAPI.getMovies();


        homeServiceCall.enqueue(new Callback<List<HomeService>>() {
            @Override
            public void onResponse(@NonNull Call<List<HomeService>> call, @NonNull Response<List<HomeService>> response) {
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

                MyApp.getInstance().setMOVIES(movieItems);

                nextActivity();
            }

            @Override
            public void onFailure(@NonNull Call<List<HomeService>> call, @NonNull Throwable t) {
                nextActivity();
                Toast toast = Toast.makeText(getApplicationContext(), "Nie udało się załatować filmów\n" + "Błąd: " + t.getMessage(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void nextActivity(){
        new Handler().postDelayed(() -> {

            startActivity(intent);
            finish();
        }, 1000);
    }

}