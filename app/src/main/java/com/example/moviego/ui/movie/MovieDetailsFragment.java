package com.example.moviego.ui.movie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.moviego.BottomNavigation;
import com.example.moviego.MyApp;
import com.example.moviego.R;
import com.example.moviego.databinding.FragmentMovieDetailsBinding;
import com.example.moviego.retrofit.DataAPI;
import com.example.moviego.retrofit.ReservedSeatsService;
import com.example.moviego.retrofit.RetrofitFunction;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsFragment extends Fragment {

    private FragmentMovieDetailsBinding binding;
    private BottomNavigation bottomNavigation;

    private int id_seansu;
    private String tytul;
    private String USER_LOGIN;
    private double cena;


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
        TextView movieRate = root.findViewById(R.id.movieRate);
        TextView movieType = root.findViewById(R.id.movieType);
        TextView movieDescription = root.findViewById(R.id.movieDescription);
        TextView movieDuration = root.findViewById(R.id.movieDuration);
        ImageView moviePoster = root.findViewById(R.id.moviePoster);

        USER_LOGIN = MyApp.getInstance().getUSER_LOGIN();


        Bundle bundle = getArguments();
        if(bundle != null){

            tytul = bundle.getString("tytul");
            String ocena = bundle.getString("ocena");
            String okladka = bundle.getString("okladka");
            String opis = bundle.getString("opis");
            String czas_trwania = bundle.getString("czas_trwania");
            String gatunek = bundle.getString("gatunek");
            id_seansu = bundle.getInt("id_seansu");
            cena = bundle.getDouble("cena");

            Bitmap bitmap = decodeBase64ToBitmap(okladka);

            movieTitle.setText(tytul);
            movieRate.setText(ocena);
            movieType.setText(gatunek);
            movieDescription.setText(opis);
            movieDuration.setText(czas_trwania);
            moviePoster.setImageBitmap(bitmap);
        }


        ImageButton backButton = root.findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) getActivity().onBackPressed();
        });

        Button bookSeats = root.findViewById(R.id.bookSeat);

        bookSeats.setOnClickListener(v -> {

            if(USER_LOGIN!=null){
                reservedSeats();
            } else {
                Toast toast = Toast.makeText(getActivity(), R.string.movie_failedBookInformaction, Toast.LENGTH_SHORT);
                toast.show();
            }
        });




        final boolean[] isClicked = {false};
        ImageButton likeMovie = root.findViewById(R.id.likeButton);
        likeMovie.setOnClickListener(v-> {
            likeMovie.setImageResource(isClicked[0] ? R.drawable.ic_cards_heart : R.drawable.mdi_cards_heart);
            isClicked[0] = !isClicked[0];
        });

        return root;
    }

    public static Bitmap decodeBase64ToBitmap(String base64Image) {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
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

    private void reservedSeats(){
        RetrofitFunction retrofitFunction = new RetrofitFunction();
        DataAPI dataAPI = retrofitFunction.dataAPI();

        Call<List<ReservedSeatsService>> reservedSeatsServiceCall = dataAPI.getReservedSeats(id_seansu);

        reservedSeatsServiceCall.enqueue(new Callback<List<ReservedSeatsService>>() {
            @Override
            public void onResponse(Call<List<ReservedSeatsService>> call, Response<List<ReservedSeatsService>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Błąd: " + response.code());
                    return;
                }

                List<ReservedSeatsService> reservedSeatsServices = response.body();
                List<Integer> rs = new ArrayList<>();

                for (ReservedSeatsService reservedSeatsService : reservedSeatsServices){
                    int seatId = reservedSeatsService.getSeatId();

                    rs.add(seatId);

                }

                Bundle book = new Bundle();
                book.putInt("book_id_seansu", id_seansu);
                book.putString("book_title", tytul);
                book.putString("book_login", USER_LOGIN);
                book.putDouble("book_price", cena);
                book.putIntegerArrayList("reservedSeats", new ArrayList<>(rs));

                MovieHallFragment nextFragment = new MovieHallFragment();
                nextFragment.setArguments(book);

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, nextFragment);
                fragmentTransaction.addToBackStack("hall");
                fragmentTransaction.commit();

            }

            @Override
            public void onFailure(Call<List<ReservedSeatsService>> call, Throwable t) {
                System.out.println("Błąd: " + t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}