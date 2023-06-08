package com.example.moviego.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.moviego.DrawerAndBottomNavActivity;
import com.example.moviego.R;
import com.example.moviego.databinding.FragmentLoginBinding;
import com.example.moviego.retrofit.DataAPI;
import com.example.moviego.retrofit.HomeService;
import com.example.moviego.retrofit.RetrofitFunction;
import com.example.moviego.ui.home.MovieItem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private TextView inputUser;
    private TextView inputPass;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }

        TextView createAccount = root.findViewById(R.id.login_createAccountText);
        createAccount.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new RegisterFragment());
            fragmentTransaction.addToBackStack("login");
            fragmentTransaction.commit();
        });

        TextView forgotPassword = root.findViewById(R.id.login_forgotPassword);
        forgotPassword.setOnClickListener(v->{
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new ForgottenPassFirstFragment());
            fragmentTransaction.addToBackStack("login");
            fragmentTransaction.commit();
        });

        inputUser = root.findViewById(R.id.login_userInput);
        inputPass = root.findViewById(R.id.login_passInput);

        Button logIn = root.findViewById(R.id.login_logIn);
        logIn.setOnClickListener(v -> {
            ((DrawerAndBottomNavActivity) getActivity()).reloadApp();
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}