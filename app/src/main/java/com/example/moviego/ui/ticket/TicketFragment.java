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

import com.example.moviego.MyApp;
import com.example.moviego.R;
import com.example.moviego.databinding.FragmentTicketBinding;
import com.example.moviego.retrofit.DataAPI;
import com.example.moviego.retrofit.RetrofitFunction;
import com.example.moviego.retrofit.TicketService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketFragment extends Fragment {

    private FragmentTicketBinding binding;
    private RecyclerView recyclerViewTickets;
    private int USER_ID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTicketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.ticket_welcome);
        }

        USER_ID = MyApp.getInstance().getUSER_ID();

        recyclerViewTickets = root.findViewById(R.id.ticket_ticketRecyclerView);

        if(USER_ID > -1) {
            getTickets();
        } else {
            List<Ticket> tickets = new ArrayList<>();
            TicketAdapter ticketAdapter = new TicketAdapter(getContext(), tickets);
            recyclerViewTickets.setAdapter(ticketAdapter);

            RecyclerView.LayoutManager layoutManagerTicket = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerViewTickets.setLayoutManager(layoutManagerTicket);
        }


        return root;
    }

    private void getTickets(){

        RetrofitFunction retrofitFunction = new RetrofitFunction();
        DataAPI dataAPI = retrofitFunction.dataAPI();

        Call<List<TicketService>> ticketServiceCall = dataAPI.getTickets(USER_ID);

        ticketServiceCall.enqueue(new Callback<List<TicketService>>() {
            @Override
            public void onResponse(@NonNull Call<List<TicketService>> call, @NonNull Response<List<TicketService>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Błąd: " + response.code());
                }

                List<TicketService> ticketServices = response.body();
                List<Ticket> tickets = new ArrayList<>();

                assert ticketServices != null;
                for (TicketService ticketService : ticketServices){

                    String title = ticketService.getTitle();
                    String reservationNumber = ticketService.getReservationNumber();
                    String dateTime = ticketService.getDateTime();
                    int seats = ticketService.getSeats();
                    String spots = ticketService.getSpots();
                    double price = ticketService.getPrice();

                    tickets.add(new Ticket(title, reservationNumber, dateTime, seats, spots, price));

                }

                TicketAdapter ticketAdapter = new TicketAdapter(getContext(), tickets);
                recyclerViewTickets.setAdapter(ticketAdapter);

                RecyclerView.LayoutManager layoutManagerTicket = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerViewTickets.setLayoutManager(layoutManagerTicket);


            }

            @Override
            public void onFailure(@NonNull Call<List<TicketService>> call, @NonNull Throwable t) {
                System.out.println("Błąd: " + t.getMessage());
            }
        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}