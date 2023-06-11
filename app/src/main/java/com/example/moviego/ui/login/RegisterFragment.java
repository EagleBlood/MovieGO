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

import com.example.moviego.R;
import com.example.moviego.databinding.FragmentRegisterBinding;
import com.example.moviego.retrofit.DataAPI;
import com.example.moviego.retrofit.RegistrationResponse;
import com.example.moviego.retrofit.RetrofitFunction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private TextView inputUser;
    private TextView inputPass;
    private TextView inputPassConfirm;
    private TextView inputEmail;
    private Button createAccButton;
    private ImageView showPassIcon;
    private ImageView showConfirmPassIcon;

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
                Toast.makeText(getContext(), R.string.reg_FillFields, Toast.LENGTH_SHORT).show();
            } else if (!pass.equals(passConfirm)) {
                Toast.makeText(getContext(), R.string.reg_PassDoNoMatch, Toast.LENGTH_SHORT).show();
            } else {
                registerUser(user, pass, email);
            }
        });

        showPassIcon = root.findViewById(R.id.reg_showPassIcon);
        showPassIcon.setOnClickListener(v -> {
            if (inputPass.getTransformationMethod() == null) {
                inputPass.setTransformationMethod(new PasswordTransformationMethod());
                showPassIcon.setImageResource(R.drawable.gridicons_visible);
            } else {
                inputPass.setTransformationMethod(null);
                showPassIcon.setImageResource(R.drawable.gridicons_invisible);
            }
        });

        showConfirmPassIcon = root.findViewById(R.id.reg_showConfirmPassIcon);
        showConfirmPassIcon.setOnClickListener(v -> {
            if (inputPassConfirm.getTransformationMethod() == null) {
                inputPassConfirm.setTransformationMethod(new PasswordTransformationMethod());
                showConfirmPassIcon.setImageResource(R.drawable.gridicons_visible);
            } else {
                inputPassConfirm.setTransformationMethod(null);
                showConfirmPassIcon.setImageResource(R.drawable.gridicons_invisible);
            }
        });

        return root;
    }

    private void registerUser(String user, String pass, String email) {
        RetrofitFunction retrofitFunction = new RetrofitFunction();
        DataAPI dataAPI = retrofitFunction.dataAPI();

        RegistrationResponse registrationResponse = new RegistrationResponse(user, pass, email);

        Call<RegistrationResponse> call = dataAPI.addUser(registrationResponse);

        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegistrationResponse> call, @NonNull Response<RegistrationResponse> response) {
                if (response.isSuccessful()) {
                    RegistrationResponse registrationResponse = response.body();
                    if (registrationResponse != null) {
                        String message = registrationResponse.getMessage();
                        if (message.equalsIgnoreCase("User registered successfully")) {
                            Toast.makeText(getContext(), R.string.reg_CreateAccText, Toast.LENGTH_SHORT).show();

                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                            fragmentManager.popBackStack();

                        } else if (message.equalsIgnoreCase("User with the given login already exists")) {
                            Toast.makeText(getContext(), R.string.reg_LoginAlready, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), R.string.reg_RegFailedText, Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getContext(), R.string.reg_RegFailedText, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegistrationResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), R.string.reg_RegFailedText, Toast.LENGTH_SHORT).show();
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