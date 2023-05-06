package com.example.moviego.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.moviego.R;
import com.example.moviego.databinding.FragmentForgottenPassSecondBinding;
import com.example.moviego.databinding.FragmentLoginBinding;

public class ForgottenPassSecondFragment extends Fragment {

    private FragmentForgottenPassSecondBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentForgottenPassSecondBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Button changePass = root.findViewById(R.id.fpSec_ChangePassButton);
        changePass.setOnClickListener(v-> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.popBackStack("login", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}