package com.example.moviego.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviego.R;
import com.example.moviego.databinding.FragmentMovieHallBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class MovieHallFragment extends Fragment {

    private FragmentMovieHallBinding binding;
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

                imageView.setOnClickListener(view -> {
                    String tag = view.getTag().toString();
                    String[] rowCol = tag.split(",");
                    int row = Integer.parseInt(rowCol[0]) + 1; // convert to 1-indexed
                    int col = Integer.parseInt(rowCol[1]) + 1; // convert to 1-indexed
                    String seat = row + ":" + col;

                    if (chosenSeats.contains(seat)) {
                        chosenSeats.remove(seat);
                        seatAdapter.removeSeat(seat);
                        recyclerView.setAdapter(seatAdapter);
                    } else {
                        chosenSeats.add(seat);
                        seatAdapter.addSeat(seat);
                        recyclerView.setAdapter(seatAdapter);
                    }


                    // change the image resource of the clicked element
                    if (chosenSeats.contains(seat)) {
                        imageView.setImageResource(R.drawable.seat_selected);
                    } else {
                        imageView.setImageResource(R.drawable.seat_available);
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
        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);


        ImageView backButton = root.findViewById(R.id.movieHall_ReturnImg);
        backButton.setOnClickListener(v -> getParentFragmentManager().popBackStack("hall", FragmentManager.POP_BACK_STACK_INCLUSIVE));

        Button bookNow = root.findViewById(R.id.movieHall_BookButton);
        bookNow.setOnClickListener(v->{
            Toast toast = Toast.makeText(getActivity(), R.string.EndTransactionText, Toast.LENGTH_SHORT);
            toast.show();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.popBackStack();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}