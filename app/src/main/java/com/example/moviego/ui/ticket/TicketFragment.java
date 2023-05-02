package com.example.moviego.ui.ticket;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviego.R;
import com.example.moviego.databinding.FragmentTicketBinding;
import com.example.moviego.ui.home.Movie;
import com.example.moviego.ui.home.MovieAdapter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketFragment extends Fragment {

    private FragmentTicketBinding binding;
    private RecyclerView recyclerViewTickets;
    private TicketAdapter ticketAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTicketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        recyclerViewTickets = root.findViewById(R.id.ticket_ticketRecyclerView);
        List<Ticket> ticketList = new ArrayList<>();

        ticketList.add(new Ticket("Filip", 49092, "03.05.2023 11:00", 2));
        ticketList.add(new Ticket("Test1", 49812, "02.05.2023 12:30", 1));
        ticketList.add(new Ticket("Test1", 49812, "02.05.2023 13:31", 3));


        ticketAdapter = new TicketAdapter(getContext(), ticketList);
        recyclerViewTickets.setAdapter(ticketAdapter);

        RecyclerView.LayoutManager layoutManagerTicket = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewTickets.setLayoutManager(layoutManagerTicket);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}