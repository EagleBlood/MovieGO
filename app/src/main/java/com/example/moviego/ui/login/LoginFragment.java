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
import com.example.moviego.MyApp;
import com.example.moviego.R;
import com.example.moviego.databinding.FragmentLoginBinding;
import com.example.moviego.retrofit.DataAPI;
import com.example.moviego.retrofit.HomeService;
import com.example.moviego.retrofit.RetrofitFunction;
import com.example.moviego.retrofit.UserService;
import com.example.moviego.ui.home.MovieItem;
import com.example.moviego.ui.profile.ProfileFragment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            String username = inputUser.getText().toString();
            String password = inputPass.getText().toString();
            performLogin(username, password);
            //((DrawerAndBottomNavActivity) getActivity()).reloadApp();
        });

        return root;
    }


    private void performLogin(String username, String password) {
        System.out.println("Start");
        RetrofitFunction retrofitFunction = new RetrofitFunction();
        DataAPI dataAPI = retrofitFunction.dataAPI();

        Call<Boolean> call = dataAPI.checkLogin(username, password); // Assuming the API returns a boolean value
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                System.out.println("OnResponse");
                if (response.isSuccessful()) {
                    boolean loginSuccessful = response.body();
                    if (loginSuccessful) {
                        // Login successful, fetch user details
                        Call<UserService> userDetailsCall = dataAPI.getUserDetails(username); // Assuming an API method to fetch user details
                        userDetailsCall.enqueue(new Callback<UserService>() {
                            @Override
                            public void onResponse(Call<UserService> call, Response<UserService> response) {
                                if (response.isSuccessful()) {
                                    UserService userService = response.body();
                                    if (userService != null) {
                                        // Store the user details or perform necessary actions
                                        int userId = userService.getId_uzyt();
                                        String userLogin = userService.getLogin();
                                        String userPassword = userService.getHaslo();
                                        String userEmail = userService.getEmail();
                                        String userFirstName = userService.getImie();
                                        String userLastName = userService.getNazwisko();
                                        String userPhone = userService.getNumer_tel();
                                        String userAddress = userService.getAdress();
                                        String userBirth = userService.getData_ur();

                                        MyApp.getInstance().setUserId(userId);
                                        MyApp.getInstance().setLogin(userLogin);

                                        Bundle bundle = new Bundle();
                                        bundle.putString("username", userLogin);
                                        bundle.putString("password", userPassword);
                                        bundle.putString("name", userFirstName);
                                        bundle.putString("surname", userLastName);
                                        bundle.putString("email", userEmail);
                                        bundle.putString("phone", userPhone);
                                        bundle.putString("address", userAddress);
                                        bundle.putString("birth", userBirth);

                                        ProfileFragment profileFragment = new ProfileFragment();
                                        profileFragment.setArguments(bundle);

                                        ((DrawerAndBottomNavActivity) requireActivity()).reloadApp();
                                        System.out.println("Login successful");
                                    }
                                } else {
                                    // Handle unsuccessful user details retrieval
                                    System.out.println("Failed to retrieve user details");
                                }
                            }

                            @Override
                            public void onFailure(Call<UserService> call, Throwable t) {
                                // Handle user details retrieval failure
                                System.out.println("Failed to retrieve user details: " + t.getMessage());
                            }
                        });
                    } else {
                        // Login failed, show an error message
                        System.out.println("Login failed");
                    }
                } else {
                    System.out.println("Request failed");
                    // Request failed, show an error message
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                // Request failed, show an error message
                System.out.println(t.getMessage());
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}