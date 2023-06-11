package com.example.moviego.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviego.R;
import com.example.moviego.retrofit.SelectedListener;

import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private final List<String> daysList;
    private int currentDatePosition;
    private final SelectedListener listener;

    public CalendarAdapter(List<String> daysList, int currentDatePosition, SelectedListener listener) {
        this.daysList = daysList;
        this.currentDatePosition = currentDatePosition;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String[] day = daysList.get(position).split(",");
        holder.itemDay.setText(day[0]);
        holder.itemWeek.setText(day[1]);

        if (position == currentDatePosition) {
            holder.itemBG.setImageResource(R.drawable.calendar_bg_active);
        } else {
            holder.itemBG.setImageResource(R.drawable.calendar_bg);
        }

        // Set the onClick listener for each calendar element
        holder.itemView.setOnClickListener(v -> {
            // Update the current selected position
            int previousPosition = currentDatePosition;
            currentDatePosition = holder.getAdapterPosition();

            // Update the background color for the previously selected item
            notifyItemChanged(previousPosition);

            // Update the background color for the currently selected item
            notifyItemChanged(currentDatePosition);

            // Update the currently picked calendar date
            String[] dayInfo = daysList.get(currentDatePosition).split(",");
            String pickedDate = dayInfo[0]; // Extract the date from "day,dayName"

            // Get the current year and month
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Months are zero-based

            // Add the year and month information to the picked date
            @SuppressLint("DefaultLocale") String formattedDate = String.format("%04d-%02d-%02d", year, month, Integer.parseInt(pickedDate));

            // Update the pickedDate variable in your fragment or any other relevant place
            // e.g., HomeFragment.pickedDate = formattedDate;

            // Pass the selected date in the desired format to the listener
            if (listener != null) {
                listener.onItemSelected(formattedDate);
            }

        });
    }

    @Override
    public int getItemCount() {
        return daysList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemBG;
        TextView itemDay;
        TextView itemWeek;

        public ViewHolder(View itemView) {
            super(itemView);
            itemDay = itemView.findViewById(R.id.itemDay);
            itemWeek = itemView.findViewById(R.id.itemWeek);
            itemBG = itemView.findViewById(R.id.itemBG);
        }

    }
}