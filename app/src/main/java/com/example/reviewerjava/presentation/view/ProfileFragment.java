package com.example.reviewerjava.presentation.view;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.example.reviewerjava.databinding.ProfileFragmentBinding;
import com.example.reviewerjava.presentation.view.adapter.ReviewListAdapter;
import com.example.reviewerjava.presentation.viewmodel.ProfileViewModel;

public class ProfileFragment extends Fragment {

    private ProfileFragmentBinding mBinding;
    private ProfileViewModel mViewModel;
    private final UserEntity user;

    public ProfileFragment(UserEntity user){
        this.user = user;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = ProfileFragmentBinding.inflate(getLayoutInflater(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        mBinding.reviewList.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.getUserReviews(user.getName()).observe(
                getViewLifecycleOwner(),
                list-> mBinding.reviewList.setAdapter(new ReviewListAdapter(
                            list,
                            (MainActivity) getActivity()
                        )
                )
        );
        if (!user.getName().equals(mViewModel.getCurrentUserName())){
            mBinding.logOut.setVisibility(View.GONE);
        }
        mBinding.logOut.setOnClickListener(v->{
            mViewModel.logOut();
            ((MainActivity) getActivity()).replaceCurrentFragment(new SignInFragment());
        });

        if(user.getAvatar() != null) {
            mBinding.profileImage.setImageBitmap(
                    BitmapFactory.decodeFile(
                            Uri.parse(user.getAvatar()).getPath()
                    )
            );
        }
        mBinding.userName.setText(user.getName());
    }
}