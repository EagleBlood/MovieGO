package com.example.moviego.ui.home;

import android.annotation.SuppressLint;
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

import com.example.moviego.MyApp;
import com.example.moviego.R;
import com.example.moviego.databinding.FragmentHomeBinding;
import com.example.moviego.retrofit.SelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment implements SelectedListener {

    private FragmentHomeBinding binding;

    private RecyclerView recyclerViewMovie1;
    private RecyclerView recyclerViewMovie2;
    private RecyclerView recyclerViewMovie3;

    private ArrayList<MovieItem> movieItems;

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
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysList, currentDatePosition, this);
        recyclerView.setAdapter(calendarAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Scroll to the position of the current date
        if (currentDatePosition != -1) {
            recyclerView.scrollToPosition(currentDatePosition);
        }

        calendarAdapter.notifyDataSetChanged();

        movieItems = MyApp.getInstance().getMovieItems();

        recyclerViewMovie1 = root.findViewById(R.id.home_movieRecyclerView1);
        recyclerViewMovie2 = root.findViewById(R.id.home_movieRecyclerView2);
        recyclerViewMovie3 = root.findViewById(R.id.home_movieRecyclerView3);

        return root;
    }


    @Override
    public void onItemClicked(int position) {
//        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
//
//        Bundle movieDetails = new Bundle();
//        movieDetails.putString("movieTitle", movieList1.get(position).getTitle());
//        movieDetailsFragment.setArguments(movieDetails);
//
//        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout, new MovieDetailsFragment());
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
    }

    @Override
    public void onItemSelected(String selectedDate) {
        String[] dateParts = selectedDate.split(",");
        String selectedDay = dateParts[0];


        ArrayList<FilteredMovie> filteredMovies = new ArrayList<>();

        for (MovieItem movieItem : movieItems) {
            String data = movieItem.getData();
            if (data.equals(selectedDay)) {
                String tytul = movieItem.getTytul();
                double ocena = Double.parseDouble(movieItem.getOcena());
                String pora_emisji = movieItem.getPora_emisji();
                filteredMovies.add(new FilteredMovie(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, tytul, ocena), pora_emisji));
            }
        }

        ArrayList<Movie> movieList1 = new ArrayList<>();
        ArrayList<Movie> movieList2 = new ArrayList<>();
        ArrayList<Movie> movieList3 = new ArrayList<>();

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

        recyclerViewMovie1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewMovie2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewMovie3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerViewMovie1.setAdapter(null);
        recyclerViewMovie2.setAdapter(null);
        recyclerViewMovie3.setAdapter(null);

        if (!movieList1.isEmpty()) {
            recyclerViewMovie1.setAdapter(new MovieAdapter(movieList1, this));
        }

        if (!movieList2.isEmpty()) {
            recyclerViewMovie2.setAdapter(new MovieAdapter(movieList2, this));
        }

        if (!movieList3.isEmpty()) {
            recyclerViewMovie3.setAdapter(new MovieAdapter(movieList3, this));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}