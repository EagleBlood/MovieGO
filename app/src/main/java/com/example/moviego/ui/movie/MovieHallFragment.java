package com.example.moviego.ui.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.moviego.R;
import com.example.moviego.databinding.FragmentLoginBinding;
import com.example.moviego.databinding.FragmentMovieHallBinding;

public class MovieHallFragment extends Fragment {

    private FragmentMovieHallBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMovieHallBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        int numRows = 8; // number of rows in the table
        int numColumns = 6; // number of columns in the table

        TableLayout tableLayout = root.findViewById(R.id.movieHall_TableLayout);

        for (int i = 0; i < numRows; i++) { // loop through rows
            TableRow tableRow = new TableRow(getContext());
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < numColumns; j++) { // loop through columns
                ImageView imageView = new ImageView(getContext());
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1f);
                imageView.setLayoutParams(layoutParams);
                imageView.setPadding(8, 8, 8, 8);
                imageView.setImageResource(R.drawable.seat_available);

                // set a tag to identify the element later
                imageView.setTag(i + "," + j);

                // set a click listener to handle click events
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tag = view.getTag().toString();
                        // handle click event for the element identified by the tag
                    }
                });

                // add the element to the row
                tableRow.addView(imageView);
            }
            // add the row to the table
            tableLayout.addView(tableRow);
        }



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}