package com.example.moviego.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviego.MyApp;
import com.example.moviego.R;
import com.example.moviego.databinding.FragmentHomeBinding;
import com.example.moviego.retrofit.DataAPI;
import com.example.moviego.retrofit.HallService;
import com.example.moviego.retrofit.SelectedListener;
import com.example.moviego.ui.movie.Hall;
import com.example.moviego.ui.movie.MovieDetailsFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements SelectedListener {

    private FragmentHomeBinding binding;

    private RecyclerView recyclerViewMovie1;
    private RecyclerView recyclerViewMovie2;
    private RecyclerView recyclerViewMovie3;
    private DataAPI dataAPI;
    private ArrayList<MovieItem> MOVIE_ITEMS;

    ArrayList<Movie> movieList1;
    ArrayList<Movie> movieList2;
    ArrayList<Movie> movieList3;

    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
            actionBar.setTitle("Hello");
        }

        RecyclerView recyclerView = root.findViewById(R.id.home_calendarRecyclerView);
        recyclerViewMovie1 = root.findViewById(R.id.home_movieRecyclerView1);
        recyclerViewMovie2 = root.findViewById(R.id.home_movieRecyclerView2);
        recyclerViewMovie3 = root.findViewById(R.id.home_movieRecyclerView3);

        // Calculate the position of the current date in the list

        setCalendar();

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

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysList, currentDatePosition, this);
        recyclerView.setAdapter(calendarAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Scroll to the position of the current date
        if (currentDatePosition != -1) {
            recyclerView.scrollToPosition(currentDatePosition);
        }
        calendarAdapter.notifyDataSetChanged();

        MOVIE_ITEMS = MyApp.getInstance().getMOVIES();

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        setAdapters(date);

        return root;
    }

    private void setCalendar() {

    }

    @Override
    public void onItemSelected(String selectedDate) {
        String[] dateParts = selectedDate.split(",");
        String selectedDay = dateParts[0];

        setAdapters(selectedDay);

    }

    private void setAdapters(String currentDate){

        ArrayList<FilteredMovie> filteredMovies = new ArrayList<>();

        for (MovieItem movieItem : MOVIE_ITEMS) {
            String data = movieItem.getData();
            if (data.equals(currentDate)) {
                String tytul = movieItem.getTytul();
                double ocena = movieItem.getOcena();
                String pora_emisji = movieItem.getPora_emisji();
                String okladka = movieItem.getOkladka();
                Bitmap bitmap = decodeBase64ToBitmap(okladka);
                filteredMovies.add(new FilteredMovie(new Movie(bitmap, tytul, ocena), pora_emisji));
            }
        }

        movieList1 = new ArrayList<>();
        movieList2 = new ArrayList<>();
        movieList3 = new ArrayList<>();

        for (FilteredMovie filteredMovie : filteredMovies) {
            Movie movie = filteredMovie.getMovie();
            String time = filteredMovie.getSelectedTime();

            switch (time) {
                case "13:00":
                    movieList1.add(movie);
                    break;
                case "16:00":
                    movieList2.add(movie);
                    break;
                case "19:00":
                    movieList3.add(movie);
                    break;
            }
        }

        if (!movieList1.isEmpty()) {
            recyclerViewMovie1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } else {
            recyclerViewMovie1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }

        if (!movieList2.isEmpty()) {
            recyclerViewMovie2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } else {
            recyclerViewMovie2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }

        if (!movieList3.isEmpty()) {
            recyclerViewMovie3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } else {
            recyclerViewMovie3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }

        recyclerViewMovie1.setAdapter(new MovieAdapter(movieList1, this::openNextFragment));

        recyclerViewMovie2.setAdapter(new MovieAdapter(movieList2, this::openNextFragment));

        recyclerViewMovie3.setAdapter(new MovieAdapter(movieList3, this::openNextFragment));
    }

    private void openNextFragment(Movie movie) {

        String tytul = movie.getTitle();
        MovieItem matchingMovieItem = null;
        for (MovieItem movieItem : MOVIE_ITEMS) {
            if (movieItem.getTytul().equals(tytul)) {
                matchingMovieItem = movieItem;
                break;
            }
        }

        if (matchingMovieItem != null) {
            // Przekazanie danych do następnego fragmentu
            Bundle bundle = new Bundle();
            bundle.putString("tytul", tytul);
            bundle.putString("ocena", String.valueOf(movie.getScore()));
            bundle.putString("okladka", matchingMovieItem.getOkladka());
            bundle.putString("opis", matchingMovieItem.getOpis());
            bundle.putString("czas_trwania", matchingMovieItem.getCzas_trwania() + " min");
            bundle.putString("gatunek", matchingMovieItem.getNazwa_gatunku());
            bundle.putInt("id_seansu", matchingMovieItem.getId_seansu());
            bundle.putDouble("cena", matchingMovieItem.getCena());

            // Otwarcie nowego fragmentu
            MovieDetailsFragment nextFragment = new MovieDetailsFragment();
            nextFragment.setArguments(bundle);

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, nextFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }

    public static Bitmap decodeBase64ToBitmap(String base64Image) {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}