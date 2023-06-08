package com.example.moviego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.moviego.retrofit.DataAPI;
import com.example.moviego.retrofit.HomeService;
import com.example.moviego.ui.home.HomeFragment;
import com.example.moviego.ui.home.Movie;
import com.example.moviego.ui.home.MovieAdapter;
import com.example.moviego.ui.home.MovieItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreen extends AppCompatActivity {

    private DataAPI dataApi;
    private ArrayList<MovieItem> movieItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent intent = new Intent(SplashScreen.this, DrawerAndBottomNavActivity.class);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(intent);
                finish();
            }
        }, 2000);




    }





}