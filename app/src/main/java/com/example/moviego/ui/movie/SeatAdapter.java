package com.example.moviego.ui.movie;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviego.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.ViewHolder> {

    private final List<String> chosenSeats;
    private final double seatPrice;


    public SeatAdapter(double seatPrice) {
        this.seatPrice = seatPrice;
        chosenSeats = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booked_seats, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String seat = chosenSeats.get(position);
        holder.seatTextView.setText(seat);
        holder.priceTextView.setText(String.format(Locale.getDefault(), "%.2f zł", seatPrice));
    }

    @Override
    public int getItemCount() {
        return chosenSeats.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addSeat(String seat) {
        chosenSeats.add(seat);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView seatTextView;
        public TextView priceTextView;

        public ViewHolder(View view) {
            super(view);
            seatTextView = view.findViewById(R.id.itemChosenSeat);
            priceTextView = view.findViewById(R.id.itemPrice);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void removeSeat(String seat) {
        chosenSeats.remove(seat);
        notifyDataSetChanged();
    }
}
