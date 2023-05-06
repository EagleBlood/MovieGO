package com.example.moviego.ui.movie;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.example.moviego.R;
import com.example.moviego.databinding.FragmentMovieHallBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class MovieHallFragment extends Fragment {

    private FragmentMovieHallBinding binding;
    private BottomSheetBehavior bottomSheetBehavior;
    private RecyclerView recyclerView;
    private List<String> chosenSeats;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMovieHallBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.movieHall_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        //List
        List<String> chosenSeats = new ArrayList<>();
        SeatAdapter seatAdapter = new SeatAdapter();

        //Table
        int numRows = 8; // number of rows in the table
        int numColumns = 9; // number of columns in the table

        TableLayout tableLayout = root.findViewById(R.id.movieHall_TableLayout);
        int middleColIndex = numColumns / 2;

        for (int i = 0; i < numRows; i++) { // loop through rows
            TableRow tableRow = new TableRow(getContext());
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < numColumns; j++) { // loop through columns
                ImageView imageView = new ImageView(getContext());
                TableRow.LayoutParams layoutParams;
                if (j == middleColIndex) { // set smaller width for middle column
                    layoutParams = new TableRow.LayoutParams(0,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            0.7f);
                } else {
                    layoutParams = new TableRow.LayoutParams(0,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            1f);
                }
                imageView.setLayoutParams(layoutParams);
                imageView.setPadding(10, 10, 10, 10);
                imageView.setImageResource(R.drawable.seat_available);

                // set a tag to identify the element later
                imageView.setTag(i + "," + j);


                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tag = view.getTag().toString();
                        imageView.setImageResource(R.drawable.seat_selected);
                        String[] rowCol = tag.split(",");
                        int row = Integer.parseInt(rowCol[0]) + 1; // convert to 1-indexed
                        int col = Integer.parseInt(rowCol[1]) + 1; // convert to 1-indexed
                        String seat = row + ":" + col;

                        // add the selected seat to the list of chosen seats
                        chosenSeats.add(seat);

                        // update the SeatAdapter with the new list of chosen seats
                        seatAdapter.addSeat(seat);

                        recyclerView.setAdapter(seatAdapter);
                    }
                });


                // add the element to the row
                tableRow.addView(imageView);
            }
            // hide the corner elements
            if (i == 0 || i == numRows - 1) {
                tableRow.getChildAt(0).setVisibility(View.INVISIBLE);
                tableRow.getChildAt(numColumns - 1).setVisibility(View.INVISIBLE);
            }

            // make middle column invisible
            tableRow.getChildAt(middleColIndex).setVisibility(View.INVISIBLE);

            // add the row to the table
            tableLayout.addView(tableRow);
        }

        //Bottom menu
        View bottomSheet = root.findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);


        ImageView backButton = root.findViewById(R.id.movieHall_ReturnImg);
        backButton.setOnClickListener(v -> getParentFragmentManager().popBackStack("hall", FragmentManager.POP_BACK_STACK_INCLUSIVE));


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}