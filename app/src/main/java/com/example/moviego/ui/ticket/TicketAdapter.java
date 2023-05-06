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

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private final List<Ticket> tickets;
    private final Context context;

    public TicketAdapter(Context context, List<Ticket> tickets) {

        this.context = context;
        this.tickets = tickets;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ticket, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.itemTitle.setText(ticket.getTitle());
        holder.itemReservationNumber.setText(String.valueOf(ticket.getReservationNumber()));
        holder.itemDate.setText(ticket.getFormattedDate());
        holder.itemSeats.setText(String.valueOf(ticket.getSeats()));
        holder.itemSpots.setText(ticket.getSpots());
        holder.itemPrice.setText(String.valueOf(ticket.getPrice()));

        LocalDateTime dateTime = ticket.getDateTime();
        LocalDateTime currentDateTime = LocalDateTime.now();
        int red = ContextCompat.getColor(context, R.color.red);

        if (dateTime.compareTo(currentDateTime) >= 0){
            holder.cardView.setCardBackgroundColor(red);
        }

        boolean isExpandable = ticket.isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticket.setExpandable(!ticket.isExpandable());
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle;
        public TextView itemReservationNumber;
        public TextView itemDate;
        public TextView itemSeats;
        public TextView itemSpots;
        public TextView itemPrice;
        public CardView cardView;
        public ConstraintLayout mainLayout;
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