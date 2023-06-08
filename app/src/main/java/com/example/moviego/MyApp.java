package com.example.moviego;

import android.app.Application;

import com.example.moviego.ui.home.MovieItem;

import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application {

    private static MyApp instance;
    private ArrayList<MovieItem> movieItems;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        movieItems = new ArrayList<>();
    }

    public static MyApp getInstance() {
        return instance;
    }

    public ArrayList<MovieItem> getMovieItems() {
        return movieItems;
    }

    public void setMovieItems(ArrayList<MovieItem> movies) {
        movieItems = movies;
    }
}