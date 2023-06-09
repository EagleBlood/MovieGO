package com.example.moviego.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviego.R;
import com.example.moviego.retrofit.OnMovieClickListener;
import com.example.moviego.retrofit.SelectedListener;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<Movie> movies;
    private final OnMovieClickListener listener;

    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public MovieAdapter(ArrayList<Movie> movies, OnMovieClickListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (movies.isEmpty()) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_EMPTY) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_no_movie, parent, false);

            return new EmptyViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
            return new MovieViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof MovieViewHolder) {
            MovieViewHolder movieViewHolder = (MovieViewHolder) viewHolder;
            Movie movie = movies.get(position);
            movieViewHolder.itemTitle.setText(movie.getTitle());
            movieViewHolder.itemScore.setText(String.valueOf(movie.getScore()));
            movieViewHolder.itemImgCard.setImageBitmap(movie.getImage());

            movieViewHolder.itemView.setOnClickListener(v -> listener.onMovieClick(movie));
        }
    }

    @Override
    public int getItemCount() {
        if (movies.isEmpty()) {
            return 1;
        } else {
            return movies.size();
        }
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

//    implements View.OnClickListener

    public static class MovieViewHolder extends RecyclerView.ViewHolder  {
        public TextView itemTitle;
        public TextView itemScore;
        public ImageView itemImgCard;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemScore = itemView.findViewById(R.id.itemScore);
            itemImgCard = itemView.findViewById(R.id.itemImgCard);

        }
    }


}