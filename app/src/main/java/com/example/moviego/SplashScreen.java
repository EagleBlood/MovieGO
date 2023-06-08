package com.example.moviego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.moviego.retrofit.DataAPI;
import com.example.moviego.retrofit.HomeService;
import com.example.moviego.retrofit.RetrofitFunction;
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

    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent intent = new Intent(SplashScreen.this, DrawerAndBottomNavActivity.class);

        RetrofitFunction.retrofit(new DataAPI() {
            @Override
            public Call<List<HomeService>> getMovies() {
                return null;
            }

            @Override
            public void onSuccess(ArrayList<MovieItem> movieItems) {
                System.out.println("Number of movie items: " + movieItems.size());

                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setMovieItems(movieItems);

               // FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
               // transaction.replace(R.id.home_movieRecyclerView1, homeFragment);
               // transaction.commit();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            }

            @Override
            public void onFailure() {
                // Handle failure case if needed
            }
        });
    }
}