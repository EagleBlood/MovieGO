package com.example.moviego.ui.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviego.R;

import java.util.ArrayList;
import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.ViewHolder> {

    private List<String> chosenSeats;
    private ArrayAdapter<String> ticketAdapter;

    public SeatAdapter() {
        chosenSeats = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booked_seats, parent, false);

        String[] ticketValues = parent.getResources().getStringArray(R.array.movieHall_TicketValues);
        ticketAdapter = new ArrayAdapter<>(parent.getContext(), R.layout.item_dropdown, ticketValues);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String seat = chosenSeats.get(position);
        holder.seatTextView.setText(seat);
        holder.ticketAdapter = ticketAdapter;
        holder.autoCompleteTextView.setAdapter(ticketAdapter);
    }

    @Override
    public int getItemCount() {
        return chosenSeats.size();
    }

    public void addSeat(String seat) {
        chosenSeats.add(seat);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView seatTextView;
        public ArrayAdapter<String> ticketAdapter;
        public AutoCompleteTextView autoCompleteTextView;

        public ViewHolder(View view) {
            super(view);
            seatTextView = view.findViewById(R.id.itemChosenSeat);
            autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        }
    }
}
