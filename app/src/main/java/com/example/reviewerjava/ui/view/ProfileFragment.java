package com.example.reviewerjava.ui.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;
import com.example.reviewerjava.databinding.ProfileFragmentBinding;
import com.example.reviewerjava.ui.view.adapter.ReviewListAdapter;
import com.example.reviewerjava.ui.viewmodel.ProfileViewModel;

public class ProfileFragment extends Fragment {

    private ProfileFragmentBinding mBinding;
    private ProfileViewModel mViewModel;
    private UserAndPermission userAndPermission;

    public ProfileFragment(UserAndPermission user){
        this.userAndPermission = user;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = ProfileFragmentBinding.inflate(getLayoutInflater(), container, false);
        mBinding.makeModeratorButton.setOnClickListener(view->{
            userAndPermission.user.setRole(UserEntity.MODERATOR);
            mViewModel.updateUser(userAndPermission.user);
        });
        mBinding.makeUserButton.setOnClickListener(view->{
            userAndPermission.user.setRole(UserEntity.USER);
            mViewModel.updateUser(userAndPermission.user);
        });
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        if(userAndPermission.user.getRole().equals(UserEntity.ADMIN)) {
            mBinding.makeModeratorButton.setVisibility(userAndPermission.permission.roleChangerAccess);
            mBinding.makeModeratorButton.setVisibility(userAndPermission.permission.roleChangerAccess);
        }
        mBinding.reviewList.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.getUserReviews(userAndPermission.user.getId()).observe(getViewLifecycleOwner(), list->{
            mBinding.reviewList.setAdapter(new ReviewListAdapter(list, (MainActivity) getActivity(), mViewModel::getUserById));
        });
    }
}