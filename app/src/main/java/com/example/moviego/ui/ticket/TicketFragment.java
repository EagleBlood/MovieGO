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
import com.example.moviego.retrofit.TicketService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TicketFragment extends Fragment {

    private FragmentTicketBinding binding;
    private DataAPI dataAPI;
    private RecyclerView recyclerViewTickets;
    private int userId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTicketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Your tickets");
        }

        userId = MyApp.getInstance().getUserId();

        recyclerViewTickets = root.findViewById(R.id.ticket_ticketRecyclerView);

        if(userId != 0) {
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

    private void retrofitCon(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataAPI = retrofit.create(DataAPI.class);
    }

    private void getTickets(){

        retrofitCon();

        Call<List<TicketService>> ticketServiceCall = dataAPI.getTickets(userId);

        ticketServiceCall.enqueue(new Callback<List<TicketService>>() {
            @Override
            public void onResponse(Call<List<TicketService>> call, Response<List<TicketService>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Błąd: " + response.code());
                }

                List<TicketService> ticketServices = response.body();
                List<Ticket> tickets = new ArrayList<>();

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
            public void onFailure(Call<List<TicketService>> call, Throwable t) {
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