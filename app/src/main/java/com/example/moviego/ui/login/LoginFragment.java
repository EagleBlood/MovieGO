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

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            try {
                connectJDBC();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return root;
    }

    private void connectJDBC() throws IOException {
        String dbUrl = LoginCredentials.getDbUrl();
        String username = String.valueOf(inputUser.getText());
        String password = String.valueOf(inputPass.getText());
        LoginCredentials.setCredentials(username, password);

        try {
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Success");
            ((DrawerAndBottomNavActivity) getActivity()).reloadApp();
            connection.close(); // Close the connection when you're done
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}