package com.example.moviego;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.moviego.ui.home.MovieItem;
import com.example.moviego.ui.login.UserData;
import com.example.moviego.ui.movie.Hall;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application {

    private static MyApp instance;
    private ArrayList<MovieItem> MOVIES;
    private List<Hall> HALLS;
    private String USER_LOGIN;
    private int USER_ID;
    private List<UserData> USER_DATA;

    private static final String SHARED_PREFS_NAME = "MovieGoPreferences";
    private static final String KEY_MOVIES = "movies";
    private static final String KEY_HALLS = "halls";

//    private static final String SHARED_PREFS_USER = "UserPreferences";
//    private static final String KEY_USER_LOGIN = "user_login";
//    private static final String KEY_USER_ID = "user_id";
//    private static final String KEY_USER_DATA = "user_data";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MOVIES = loadMovies();
        HALLS = loadHalls();
        USER_LOGIN = null;
        USER_ID = -1;
        USER_DATA = new ArrayList<>();
    }

    public void saveMovies(ArrayList<MovieItem> list) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(KEY_MOVIES, json);
        editor.apply();
    }

    public ArrayList<MovieItem> loadMovies() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_MOVIES, null);
        Type type = new TypeToken<ArrayList<MovieItem>>() {}.getType();
        MOVIES = gson.fromJson(json, type);
        return gson.fromJson(json, type);
    }

    public void saveHalls(List<Hall> list) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(KEY_HALLS, json);
        editor.apply();
    }

    public List<Hall> loadHalls() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_HALLS, null);
        Type type = new TypeToken<List<Hall>>() {}.getType();
        HALLS = gson.fromJson(json, type);
        return gson.fromJson(json, type);
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