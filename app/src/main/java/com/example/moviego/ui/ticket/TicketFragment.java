package com.example.moviego.ui.ticket;

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
import com.example.moviego.databinding.FragmentTicketBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketFragment extends Fragment {

    private FragmentTicketBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTicketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Your tickets");
        }

        RecyclerView recyclerViewTickets = root.findViewById(R.id.ticket_ticketRecyclerView);
        List<Ticket> ticketList = new ArrayList<>();

        ticketList.add(new Ticket("Filip", 49092, "03.05.2023 11:00", 2, new ArrayList<>(Arrays.asList("2:4", "4:6")), 28.98));
        ticketList.add(new Ticket("Test1", 49812, "02.05.2023 12:30", 1, new ArrayList<>(Arrays.asList("1:6", "4:6")), 14.49));
        ticketList.add(new Ticket("Test1", 49812, "02.05.2023 13:31", 3, new ArrayList<>(Arrays.asList("9:2", "9:3", "9:4")), 43.47));


        TicketAdapter ticketAdapter = new TicketAdapter(getContext(), ticketList);
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