package com.example.reviewerjava.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.databinding.RoleChangerFragmentBinding;
import com.example.reviewerjava.ui.view.adapter.RoleChangerAdapter;
import com.example.reviewerjava.ui.viewmodel.RoleChangerViewModel;
import com.example.reviewerjava.utils.Adminer;

public class RoleChangerFragment extends Fragment implements Adminer {
    RoleChangerFragmentBinding binding;
    RoleChangerViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = RoleChangerFragmentBinding.inflate(inflater);
        binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(RoleChangerViewModel.class);
        viewModel.getUsers().observe(getViewLifecycleOwner(), list->{
            binding.list.setAdapter(new RoleChangerAdapter(list, this));
        });
    }

    @Override
    public void ban(UserEntity user) {
        viewModel.ban(user);
    }

    @Override
    public void updateUser(UserEntity user, String newRole) {
        viewModel.updateUser(user, newRole);
    }


    @Override
    public void checkUser(UserEntity user) {
        ((MainActivity) getActivity()).setFragment(new ProfileFragment(user));
    }
}
