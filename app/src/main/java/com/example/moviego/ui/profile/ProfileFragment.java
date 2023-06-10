package com.example.moviego.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.moviego.R;
import com.example.moviego.databinding.FragmentProfileBinding;
import com.example.moviego.retrofit.UserService;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String birth;

    private TextView editProfileInput;
    private TextView editPassInput;
    private TextView editNameInput;
    private TextView editSurnameInput;
    private TextView editEmailInput;
    private TextView editPhoneInput;
    private TextView editAddrInput;
    private TextView editBirthInput;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        editProfileInput = root.findViewById(R.id.editProfileInput);
        editPassInput = root.findViewById(R.id.editPassInput);
        editNameInput = root.findViewById(R.id.editNameInput);
        editSurnameInput = root.findViewById(R.id.editSurnameInput);
        editEmailInput = root.findViewById(R.id.editEmailInput);
        editPhoneInput = root.findViewById(R.id.editPhoneInput);
        editAddrInput = root.findViewById(R.id.editAddrInput);
        editBirthInput = root.findViewById(R.id.editBirthInput);

        Bundle bundle = getArguments();
        if (bundle != null) {
            username = bundle.getString("username");
            password = bundle.getString("password");
            name = bundle.getString("name");
            surname = bundle.getString("surname");
            email = bundle.getString("email");
            phone = bundle.getString("phone");
            address = bundle.getString("address");
            birth = bundle.getString("birth");

            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Name: " + name);
            System.out.println("Surname: " + surname);
            System.out.println("Email: " + email);
            System.out.println("Phone: " + phone);

            binding.editProfileInput.setText(username);
            binding.editPassInput.setText(password);
            binding.editNameInput.setText(name);
            binding.editSurnameInput.setText(surname);
            binding.editEmailInput.setText(email);
            binding.editPhoneInput.setText(phone);
            binding.editAddrInput.setText(address);
            binding.editBirthInput.setText(birth);
        }



        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Hello, " + username);
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}