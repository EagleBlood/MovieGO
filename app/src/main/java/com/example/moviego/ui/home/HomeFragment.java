package com.example.moviego.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviego.R;
import com.example.moviego.databinding.FragmentHomeBinding;
import com.example.moviego.retrofit.DataAPI;
import com.example.moviego.retrofit.HomeService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerViewMovie1;
    private RecyclerView recyclerViewMovie2;
    private RecyclerView recyclerViewMovie3;
    private RecyclerView recyclerViewMovie4;
    private MovieAdapter movieAdapter;
    private DataAPI dataApi;
    private ArrayList<MovieItem> movieItems;
    private List<Movie> movieList1;

    public static HomeFragment newInstance(ArrayList<MovieItem> data) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable("movies", data);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
            actionBar.setTitle("Hello, ...");
        }

        recyclerViewMovie1 = root.findViewById(R.id.home_movieRecyclerView1);
        recyclerViewMovie2 = root.findViewById(R.id.home_movieRecyclerView2);
        recyclerViewMovie3 = root.findViewById(R.id.home_movieRecyclerView3);


        retrofit();


        List<Movie> movieList1 = new ArrayList<>();



        movieAdapter = new MovieAdapter(getContext(), movieList1);
        recyclerViewMovie2.setAdapter(movieAdapter);

        // Calculate the position of the current date in the list
        List<String> daysList = new ArrayList<>();

        int currentDatePosition = -1;
        String currentDate = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());

        // Add the days of the week to the daysList
        for (int i = -7; i < 14; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar.add(Calendar.DATE, i);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd", Locale.getDefault());
            String dayNumber = dateFormat.format(calendar.getTime());
            String dayName = new SimpleDateFormat("E", Locale.getDefault()).format(calendar.getTime());
            String day = dayNumber + "," + dayName;
            daysList.add(day);

            if (dayNumber.equals(currentDate)) {
                currentDatePosition = daysList.size() - 1;
            }
        }

        RecyclerView recyclerView = root.findViewById(R.id.home_calendarRecyclerView);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysList, currentDatePosition);
        recyclerView.setAdapter(calendarAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Scroll to the position of the current date
        if (currentDatePosition != -1) {
            recyclerView.scrollToPosition(currentDatePosition);
        }

        calendarAdapter.notifyDataSetChanged();




// Tworzenie klienta dla klasy UserData
//        RetrofitClient<UserDataAPI> userDataClient = new RetrofitClient<>(UserDataAPI.class);
//
//// Wywołanie metody POST dla UserData
//        UserData userData = new UserData("John", "Doe");
//        Call<UserData> userDataCall = userDataClient.getDataAPI().postUserData(userData);
//        userDataClient.sendData(userDataCall, new Callback<UserData>() {
//            @Override
//            public void onResponse(Call<UserData> call, Response<UserData> response) {
//                // Obsługa odpowiedzi
//            }
//
//            @Override
//            public void onFailure(Call<UserData> call, Throwable t) {
//                // Obsługa błędu
//            }
//        });


        // Movies
            //RecyclerView Movie 11AM
        // Create an instance of the RetrofitFunction class





//        RecyclerView Movie 3PM

        List<Movie> movieList2 = new ArrayList<>();

//         Some dummy movies for testing
        movieList2.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 1", 7.5f));
        movieList2.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 2", 8.0f));
        movieList2.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 3", 6.5f));

        movieAdapter = new MovieAdapter(getContext(), movieList2);
        recyclerViewMovie2.setAdapter(movieAdapter);

//        for(MovieItem movieItem : movieItems) {
//            System.out.println("Tytuł: " + movieItem.getTytul());
//        }

        RecyclerView.LayoutManager layoutManagerMovie2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewMovie2.setLayoutManager(layoutManagerMovie2);



            //RecyclerView Movie 5PM
//        recyclerViewMovie3 = root.findViewById(R.id.home_movieRecyclerView3);
//        //List<Movie> movieList3 = new ArrayList<>();
//
//        // Some dummy movies for testing
//        //movieList3.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 1", 7.5f));
//        //movieList3.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 2", 8.0f));
//        //movieList3.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 3", 6.5f));
//
//        movieAdapter = new MovieAdapter(getContext(),movieList);
//        recyclerViewMovie3.setAdapter(movieAdapter);
//
//        RecyclerView.LayoutManager layoutManagerMovie3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerViewMovie3.setLayoutManager(layoutManagerMovie3);


        return root;
    }

    private void retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataApi = retrofit.create(DataAPI.class);

        Call<List<HomeService>> homeServiceCall = dataApi.getMovies();

        homeServiceCall.enqueue(new Callback<List<HomeService>>() {
            @Override
            public void onResponse(Call<List<HomeService>> call, Response<List<HomeService>> response) {
                if(!response.isSuccessful()) {
                    System.out.println("Błąd: " + response.code());
                    return;
                }

                List<HomeService> homeServices = response.body();
                movieItems = new ArrayList<>();
                assert homeServices != null;
                for (HomeService homeService : homeServices ){

                    String id_filmu = homeService.getId_filmu();
                    String tytul = homeService.getTytul();
                    String czas_trwania = homeService.getCzas_trwania();
                    String ocena = homeService.getOcena();
                    String opis = homeService.getOpis();
                    String okladka = homeService.getOkladka();
                    String cena = homeService.getCena();
                    String nazwa_gatunku = homeService.getNazwa_gatunku();
                    String data = homeService.getData();
                    String pora_emisji = homeService.getPora_emisja();

                    movieItems.add(new MovieItem(id_filmu, tytul, czas_trwania, ocena, opis, okladka, cena, nazwa_gatunku, data, pora_emisji));

                }

                recycler();

            }

            @Override
            public void onFailure(Call<List<HomeService>> call, Throwable t) {
                System.out.println("Błąd: " + t.getMessage());
            }

        });

    }

    private void recycler() {
        System.out.println("Rozmiar: " + movieItems.size());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}