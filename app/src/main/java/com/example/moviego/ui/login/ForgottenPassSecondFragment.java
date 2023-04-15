package com.example.moviego.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviego.R;
import com.example.moviego.databinding.FragmentForgottenPassSecondBinding;
import com.example.moviego.databinding.FragmentLoginBinding;

public class ForgottenPassSecondFragment extends Fragment {

    private FragmentForgottenPassSecondBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentForgottenPassSecondBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}