package com.example.moviego.ui.movie;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.moviego.BottomNavigation;
import com.example.moviego.R;
import com.example.moviego.databinding.FragmentMovieDetailsBinding;

public class MovieDetailsFragment extends Fragment {

    private FragmentMovieDetailsBinding binding;
    private BottomNavigation bottomNavigation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        bottomNavigation.hideElement();

        TextView movieTitle = root.findViewById(R.id.movieTitle);


        Bundle bundle = getArguments();
        if(bundle != null){
            String title = bundle.getString("movieTitle");
            movieTitle.setText(title);
        }


        ImageButton backButton = root.findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) getActivity().onBackPressed();
        });

        Button bookSeats = root.findViewById(R.id.bookSeat);
        bookSeats.setOnClickListener(v -> {
            FragmentManager fragmentManager = (requireActivity()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new MovieHallFragment());
            fragmentTransaction.addToBackStack("hall");
            fragmentTransaction.commit();
        });

        final boolean[] isClicked = {false};
        ImageButton likeMovie = root.findViewById(R.id.likeButton);
        likeMovie.setOnClickListener(v-> {
            likeMovie.setImageResource(isClicked[0] ? R.drawable.ic_cards_heart : R.drawable.mdi_cards_heart);
            isClicked[0] = !isClicked[0];
        });

        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BottomNavigation) {
            bottomNavigation = (BottomNavigation) context;
        } else {
            throw new RuntimeException(context
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}