package com.example.reviewerjava.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.databinding.RegisterFragmentBinding;
import com.example.reviewerjava.ui.viewmodel.RegisterViewModel;


public class RegisterFragment extends Fragment {
    private RegisterFragmentBinding mBinding;
    private RegisterViewModel mViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = RegisterFragmentBinding.inflate(inflater, container, false);
        mBinding.login.setOnClickListener(view -> {
            if(mViewModel.logIn(
                    mBinding.editTextLogin.getText().toString(),
                    mBinding.editTextPassword.getText().toString()
            )) {
                Toast.makeText(getContext(), "Logged in", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).popBackStack();
            }
            else Toast.makeText(getContext(), "Wrong Log/Pass", Toast.LENGTH_SHORT).show();
        });

        mBinding.signIn.setOnClickListener(view->{
            mViewModel.signIn(
                    mBinding.editTextLogin.getText().toString(),
                    mBinding.editTextPassword.getText().toString()
            );
        });
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        super.onViewCreated(view, savedInstanceState);
    }
}
