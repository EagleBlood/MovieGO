package com.example.moviego.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.metrics.Event;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviego.R;
import com.example.moviego.databinding.FragmentHomeBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewMovie1;
    private RecyclerView recyclerViewMovie2;
    private RecyclerView recyclerViewMovie3;
    private RecyclerView recyclerViewMovie4;
    private MovieAdapter movieAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
            actionBar.setTitle("Home");
        }


        // Calendar
        recyclerView = root.findViewById(R.id.home_calendarRecyclerView);
        List<String> daysList = new ArrayList<>();

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysList);
        recyclerView.setAdapter(calendarAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Get the calendar instance and set it to the first day of the week
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        // Add the days of the week to the daysList
        for (int i = 0; i < 14; i++) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd", Locale.getDefault());
            String dayNumber = dateFormat.format(calendar.getTime());
            String dayName = new SimpleDateFormat("E", Locale.getDefault()).format(calendar.getTime());
            daysList.add(dayNumber + "," + dayName);
            calendar.add(Calendar.DATE, 1);
        }
        calendarAdapter.notifyDataSetChanged();



        // Movies
            //RecyclerView Movie 11AM
        recyclerViewMovie1 = root.findViewById(R.id.home_movieRecyclerView1);
        List<Movie> movieList = new ArrayList<>();

        // Some dummy movies for testing
        movieList.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 1", 7.5f));
        movieList.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 2", 8.0f));
        movieList.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 3", 6.5f));

        movieAdapter = new MovieAdapter(movieList);
        recyclerViewMovie1.setAdapter(movieAdapter);

        RecyclerView.LayoutManager layoutManagerMovie1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewMovie1.setLayoutManager(layoutManagerMovie1);



            //RecyclerView Movie 3PM
        recyclerViewMovie2 = root.findViewById(R.id.home_movieRecyclerView2);
        //List<Movie> movieList2 = new ArrayList<>();

        // Some dummy movies for testing
        //movieList2.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 1", 7.5f));
        //movieList2.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 2", 8.0f));
        //movieList2.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 3", 6.5f));

        movieAdapter = new MovieAdapter(movieList);
        recyclerViewMovie2.setAdapter(movieAdapter);

        RecyclerView.LayoutManager layoutManagerMovie2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewMovie2.setLayoutManager(layoutManagerMovie2);



            //RecyclerView Movie 5PM
        recyclerViewMovie3 = root.findViewById(R.id.home_movieRecyclerView3);
        //List<Movie> movieList3 = new ArrayList<>();

        // Some dummy movies for testing
        //movieList3.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 1", 7.5f));
        //movieList3.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 2", 8.0f));
        //movieList3.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 3", 6.5f));

        movieAdapter = new MovieAdapter(movieList);
        recyclerViewMovie3.setAdapter(movieAdapter);

        RecyclerView.LayoutManager layoutManagerMovie3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewMovie3.setLayoutManager(layoutManagerMovie3);



            //RecyclerView Movie 7PM
        recyclerViewMovie4 = root.findViewById(R.id.home_movieRecyclerView4);
        //List<Movie> movieList3 = new ArrayList<>();

        // Some dummy movies for testing
        //movieList3.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 1", 7.5f));
        //movieList3.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 2", 8.0f));
        //movieList3.add(new Movie(R.drawable.filip_b1_b_cut_f762836d12_3, "Title 3", 6.5f));

        movieAdapter = new MovieAdapter(movieList);
        recyclerViewMovie4.setAdapter(movieAdapter);

        RecyclerView.LayoutManager layoutManagerMovie4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewMovie4.setLayoutManager(layoutManagerMovie4);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}