package com.example.moviego;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviego.retrofit.DataAPI;
import com.example.moviego.retrofit.HomeService;
import com.example.moviego.retrofit.RetrofitFunction;
import com.example.moviego.ui.home.MovieItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent intent = new Intent(this, DrawerAndBottomNavActivity.class);

        RetrofitFunction.retrofit(new DataAPI() {


            @Override
            public Call<List<HomeService>> getMovies() {
                return null;
            }

            @Override
            public void onSuccess(ArrayList<MovieItem> movieItems) {
                System.out.println("Number of movie items: " + movieItems.size());

                MyApp.getInstance().setMovieItems(movieItems);

                new Handler().postDelayed(() -> {
                    startActivity(intent);
                    finish();
                }, 1000);
            }


            @Override
            public void onFailure() {
                // Handle failure case if needed
            }
        });
    }

}