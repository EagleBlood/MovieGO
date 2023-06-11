package com.example.moviego.ui.login;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.moviego.retrofit.RetrofitFunction;
import com.example.moviego.retrofit.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private TextView inputUser;
    private TextView inputPass;
    private ImageView showPassIcon;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
            actionBar.setTitle(R.string.login_welcome);
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

        showPassIcon = root.findViewById(R.id.login_showPassIcon);
        showPassIcon.setOnClickListener(v -> {
            if (inputPass.getTransformationMethod() == null) {
                inputPass.setTransformationMethod(new PasswordTransformationMethod());
                showPassIcon.setImageResource(R.drawable.gridicons_visible);
            } else {
                inputPass.setTransformationMethod(null);
                showPassIcon.setImageResource(R.drawable.gridicons_invisible);
            }
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
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                System.out.println("OnResponse");
                if (!response.isSuccessful()) {
                    System.out.println("Login failed");
                }

                boolean loginSuccessful = Boolean.TRUE.equals(response.body());

                if (loginSuccessful) {
                    // Login successful, fetch user details
                    Call<UserService> userDetailsCall = dataAPI.getUserDetails(username); // Assuming an API method to fetch user details
                    userDetailsCall.enqueue(new Callback<UserService>() {
                        @Override
                        public void onResponse(@NonNull Call<UserService> call, @NonNull Response<UserService> response) {
                            if (response.isSuccessful()) {
                                UserService userService = response.body();
                                if (userService != null) {
                                    // Store the user details or perform necessary actions
                                    int userId = userService.getId();
                                    String userLogin = userService.getLogin();
                                    String userPassword = userService.getPassword();
                                    String userEmail = userService.getEmail();
                                    String userFirstName = userService.getName();
                                    String userLastName = userService.getSurname();
                                    String userPhone = userService.getNumber();
                                    String userAddress = userService.getAddress();
                                    String userBirth = userService.getBirthdate();

                                    MyApp.getInstance().setUSER_ID(userId);
                                    MyApp.getInstance().setUSER_LOGIN(userLogin);

                                    List<UserData> userData = new ArrayList<>();

                                    userData.add(new UserData(userPassword, userEmail, userFirstName, userLastName, userPhone, userAddress, userBirth));

                                    MyApp.getInstance().setUSER_DATA(userData);

                                    ((DrawerAndBottomNavActivity) requireActivity()).reloadApp();
                                    Toast.makeText(getContext(), R.string.toastLoginSuccess, Toast.LENGTH_SHORT).show();
                                    System.out.println("Login successful");
                                }
                            } else {
                                // Handle unsuccessful user details retrieval
                                System.out.println("Failed to retrieve user details");
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<UserService> call, @NonNull Throwable t) {
                            // Handle user details retrieval failure
                            System.out.println("Failed to retrieve user details: " + t.getMessage());
                        }
                    });
                } else {
                    Toast.makeText(getContext(), R.string.toastLoginFailed, Toast.LENGTH_SHORT).show();
                    System.out.println("Request failed");
                    // Request failed, show an error message
                }
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
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