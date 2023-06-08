package com.example.moviego.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviego.R;
import com.example.moviego.retrofit.SelectedListener;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final ArrayList<Movie> movies;
    private final SelectedListener listener;

    public MovieAdapter(ArrayList<Movie> movies, SelectedListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);

        return new MovieViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.itemTitle.setText(movie.getTitle());
        holder.itemScore.setText(String.valueOf(movie.getScore()));
        holder.itemImgCard.setImageBitmap(movie.getImage());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

//    implements View.OnClickListener

    public static class MovieViewHolder extends RecyclerView.ViewHolder  {
        public TextView itemTitle;
        public TextView itemScore;
        public ImageView itemImgCard;

        public MovieViewHolder(@NonNull View itemView, SelectedListener listener) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemScore = itemView.findViewById(R.id.itemScore);
            itemImgCard = itemView.findViewById(R.id.itemImgCard);
            itemView.setOnClickListener(view -> {
                if (listener != null){
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){
                        listener.onItemClicked(pos);
                    }
                }
            });
        }
    }


}