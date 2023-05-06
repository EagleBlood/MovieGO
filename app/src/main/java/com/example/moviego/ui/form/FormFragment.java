package com.example.moviego.ui.form;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.moviego.R;
import com.example.moviego.databinding.FragmentFormBinding;

public class FormFragment extends Fragment {

    private FragmentFormBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFormBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button sendMessage = root.findViewById(R.id.form_SubjectButton);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}