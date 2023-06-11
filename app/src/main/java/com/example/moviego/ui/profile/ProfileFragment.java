package com.example.moviego.ui.profile;

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

import com.example.moviego.DrawerAndBottomNavActivity;
import com.example.moviego.MyApp;
import com.example.moviego.R;
import com.example.moviego.databinding.FragmentProfileBinding;
import com.example.moviego.retrofit.UserService;
import com.example.moviego.ui.login.UserData;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private String USER_LOGIN;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String birth;
    private Button editLogoutButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        USER_LOGIN = MyApp.getInstance().getUSER_LOGIN();
        List<UserData> USER_DATA = MyApp.getInstance().getUSER_DATA();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            String welcomeString = getString(R.string.welcome_login);
            String combinedString = welcomeString + " " + USER_LOGIN;
            actionBar.setTitle(combinedString);
        }

        TextView editProfileInput = root.findViewById(R.id.editProfileInput);
        TextView editPassInput = root.findViewById(R.id.editPassInput);
        TextView editNameInput = root.findViewById(R.id.editNameInput);
        TextView editSurnameInput = root.findViewById(R.id.editSurnameInput);
        TextView editEmailInput = root.findViewById(R.id.editEmailInput);
        TextView editPhoneInput = root.findViewById(R.id.editPhoneInput);
        TextView editAddrInput = root.findViewById(R.id.editAddrInput);
        TextView editBirthInput = root.findViewById(R.id.editBirthInput);


        if(USER_DATA.size() == 1 && USER_LOGIN != null){
            for(UserData userData : USER_DATA){
                password = userData.getPassword();
                name = userData.getName();
                surname = userData.getSurname();
                email = userData.getEmail();
                phone = userData.getNumber();
                address = userData.getAddress();
                birth = userData.getBirthdate();
            }

            editProfileInput.setText(USER_LOGIN);
            editPassInput.setText(password);
            editNameInput.setText(name);
            editSurnameInput.setText(surname);
            editEmailInput.setText(email);
            editPhoneInput.setText(phone);
            editAddrInput.setText(address);
            editBirthInput.setText(birth);
        }

        editLogoutButton = root.findViewById(R.id.editLogoutButton);
        editLogoutButton.setOnClickListener(v -> {
            MyApp.getInstance().setUSER_LOGIN(null);
            MyApp.getInstance().setUSER_DATA(new ArrayList<>());
            MyApp.getInstance().setUSER_ID(0);
            ((DrawerAndBottomNavActivity) requireActivity()).reloadApp();
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}