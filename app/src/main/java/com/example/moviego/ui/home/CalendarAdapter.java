package com.example.moviego.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviego.R;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private List<String> daysList;

    public CalendarAdapter(List<String> daysList) {
        this.daysList = daysList;
    }

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

        String currentDate = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        if (day[0].equals(currentDate)) {
            holder.itemBG.setImageResource(R.drawable.calendar_bg_active);
        } else {
            holder.itemBG.setImageResource(R.drawable.calendar_bg);
        }
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