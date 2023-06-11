package com.example.moviego.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.moviego.R;
import com.example.moviego.databinding.FragmentRegisterBinding;
import com.example.moviego.retrofit.BookResponse;
import com.example.moviego.retrofit.DataAPI;
import com.example.moviego.retrofit.RegistrationRequest;
import com.example.moviego.retrofit.RegistrationResponse;
import com.example.moviego.retrofit.RetrofitFunction;

import retrofit2.Call;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private TextView inputUser;
    private TextView inputPass;
    private TextView inputPassConfirm;
    private TextView inputEmail;
    private Button createAccButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Button signUp = root.findViewById(R.id.reg_createAccButton);
        signUp.setOnClickListener(v->{
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.popBackStack("login", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        });

        TextView logIn = root.findViewById(R.id.reg_bottomText);
        logIn.setOnClickListener(v->{
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.popBackStack("login", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        });

        inputEmail = root.findViewById(R.id.reg_emailInput);
        inputUser = root.findViewById(R.id.reg_loginInput);
        inputPass = root.findViewById(R.id.reg_passInput);
        inputPassConfirm = root.findViewById(R.id.reg_confirmPassInput);
        createAccButton = root.findViewById(R.id.reg_createAccButton);
        createAccButton.setOnClickListener(v->{
            String email = inputEmail.getText().toString();
            String user = inputUser.getText().toString();
            String pass = inputPass.getText().toString();
            String passConfirm = inputPassConfirm.getText().toString();
            if (email.isEmpty() || user.isEmpty() || pass.isEmpty() || passConfirm.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (!pass.equals(passConfirm)) {
                Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(email, user, pass);
            }
        });

        return root;
    }

    private void registerUser(String user, String pass, String email) {
        RetrofitFunction retrofitFunction = new RetrofitFunction();
        DataAPI dataAPI = retrofitFunction.dataAPI();

        Call<RegistrationResponse> call = dataAPI.registerUser(user, pass, email);

        call.enqueue(new retrofit2.Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, retrofit2.Response<RegistrationResponse> response) {
                if (response.isSuccessful()) {
                    RegistrationResponse registrationResponse = response.body();
                    if (registrationResponse != null) {
                        String message = registrationResponse.getMessage();
                        if (message.equalsIgnoreCase("User registered successfully")) {
                            Toast.makeText(getContext(), "Account created", Toast.LENGTH_SHORT).show();
                        } else if (message.equalsIgnoreCase("User with the given login already exists")) {
                            Toast.makeText(getContext(), "Login already taken", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                System.out.println("Failure: " + t.getMessage());
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}