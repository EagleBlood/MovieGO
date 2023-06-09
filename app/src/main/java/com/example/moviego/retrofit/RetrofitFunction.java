package com.example.moviego.retrofit;

import com.example.moviego.ui.home.MovieItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFunction<T> {

    private static DataAPI dataAPI;

    public RetrofitFunction() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataAPI = retrofit.create(DataAPI.class);
    }


    public DataAPI dataAPI() {
        return dataAPI;
    }

//    public static void retrofit(final DataAPI callback) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:8080/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        dataAPI = retrofit.create(DataAPI.class);
//
//        Call<List<HomeService>> homeServiceCall = dataAPI.getMovies();
//
//        homeServiceCall.enqueue(new Callback<List<HomeService>>() {
//            @Override
//            public void onResponse(Call<List<HomeService>> call, Response<List<HomeService>> response) {
//                if (!response.isSuccessful()) {
//                    System.out.println("Błąd: " + response.code());
//                    callback.onFailure();
//                    return;
//                }
//
//                List<HomeService> homeServices = response.body();
//                ArrayList<MovieItem> movieItems = new ArrayList<>();
//
//                if (homeServices != null) {
//                    for (HomeService homeService : homeServices) {
//                        String id_filmu = homeService.getId_filmu();
//                        String tytul = homeService.getTytul();
//                        String czas_trwania = homeService.getCzas_trwania();
//                        String ocena = homeService.getOcena();
//                        String opis = homeService.getOpis();
//                        String okladka = homeService.getOkladka();
//                        String cena = homeService.getCena();
//                        String nazwa_gatunku = homeService.getNazwa_gatunku();
//                        String data = homeService.getData();
//                        String pora_emisji = homeService.getPora_emisja();
//
//                        movieItems.add(new MovieItem(id_filmu, tytul, czas_trwania, ocena, opis, okladka, cena, nazwa_gatunku, data, pora_emisji));
//                    }
//                }
//
//                callback.onSuccess(movieItems);
//            }
//
//            @Override
//            public void onFailure(Call<List<HomeService>> call, Throwable t) {
//                System.out.println("Błąd: " + t.getMessage());
//                callback.onFailure();
//            }
//        });
//    }
}

