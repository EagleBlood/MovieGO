package com.example.moviego;

import android.app.Application;

import com.example.moviego.ui.home.MovieItem;
import com.example.moviego.ui.movie.Hall;

import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application {

    private static MyApp instance;
    private ArrayList<MovieItem> movieItems;
    private List<Hall> halls;
    private String login;
    private int userId;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        movieItems = new ArrayList<>();
        halls = new ArrayList<>();
        login = null;
        userId = 0;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }
}