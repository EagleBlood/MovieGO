package com.example.moviego.ui.ticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviego.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Ticket> tickets;
    private final Context context;

    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public TicketAdapter(Context context, List<Ticket> tickets) {

        this.context = context;
        this.tickets = tickets;
    }

    @Override
    public int getItemViewType(int position) {
        if (tickets.isEmpty()) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_EMPTY) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_no_ticket, parent, false);

            return new TicketAdapter.EmptyViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
            return new TicketAdapter.TicketViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof TicketViewHolder) {
            TicketViewHolder holder = (TicketViewHolder) viewHolder;
            Ticket ticket = tickets.get(position);
            holder.itemTitle.setText(ticket.getTitle());
            holder.itemReservationNumber.setText(String.valueOf(ticket.getReservationNumber()));
            holder.itemDate.setText(ticket.getDateTime());
            holder.itemSeats.setText(String.valueOf(ticket.getSeats()));
            holder.itemSpots.setText(ticket.getSpots());
            holder.itemPrice.setText(String.valueOf(ticket.getPrice()));

            String dateTimeString = ticket.getDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
            LocalDateTime currentDateTime = LocalDateTime.now();
            int red = ContextCompat.getColor(context, R.color.red);

            if (dateTime.compareTo(currentDateTime) >= 0) {
                holder.cardView.setCardBackgroundColor(red);
            }

            boolean isExpandable = ticket.isExpandable();
            holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

            holder.cardView.setOnClickListener(v -> {
                ticket.setExpandable(!ticket.isExpandable());
                notifyItemChanged(holder.getAdapterPosition());
            });
        }
    }

    @Override
    public int getItemCount() {
        if (tickets.isEmpty()) {
            return 1;
        } else {
            return tickets.size();
        }
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle;
        public TextView itemReservationNumber;
        public TextView itemDate;
        public TextView itemSeats;
        public TextView itemSpots;
        public TextView itemPrice;
        public CardView cardView;
        public ConstraintLayout expandableLayout;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemReservationNumber = itemView.findViewById(R.id.itemReservation);
            itemDate = itemView.findViewById(R.id.itemBooking);
            itemSeats = itemView.findViewById(R.id.itemSeats);
            cardView = itemView.findViewById(R.id.cardView);
            itemSpots = itemView.findViewById(R.id.itemSpots);
            itemPrice = itemView.findViewById(R.id.itemPriceValue);
            expandableLayout = itemView.findViewById(R.id.ticket_expandableLayout);

        }
    }
}