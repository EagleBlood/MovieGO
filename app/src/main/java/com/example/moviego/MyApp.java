package com.example.moviego;

import android.app.Application;

import com.example.moviego.ui.home.MovieItem;
import com.example.moviego.ui.login.UserData;
import com.example.moviego.ui.movie.Hall;

import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application {

    private static MyApp instance;
    private ArrayList<MovieItem> MOVIES;
    private List<Hall> HALLS;
    private String USER_LOGIN;
    private int USER_ID;
    private List<UserData> USER_DATA;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MOVIES = new ArrayList<>();
        HALLS = new ArrayList<>();
        USER_LOGIN = null;
        USER_ID = -1;
        USER_DATA = new ArrayList<>();
    }

    public static MyApp getInstance() {
        return instance;
    }

    public ArrayList<MovieItem> getMOVIES() {
        return MOVIES;
    }

    public void setMOVIES(ArrayList<MovieItem> MOVIES) {
        this.MOVIES = MOVIES;
    }

    public List<Hall> getHALLS() {
        return HALLS;
    }

    public void setHALLS(List<Hall> HALLS) {
        this.HALLS = HALLS;
    }

    public String getUSER_LOGIN() {
        return USER_LOGIN;
    }

    public void setUSER_LOGIN(String USER_LOGIN) {
        this.USER_LOGIN = USER_LOGIN;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public List<UserData> getUSER_DATA() {
        return USER_DATA;
    }

    public void setUSER_DATA(List<UserData> USER_DATA) {
        this.USER_DATA = USER_DATA;
    }
}