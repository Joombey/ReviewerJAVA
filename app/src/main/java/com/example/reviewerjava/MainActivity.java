package com.example.reviewerjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.reviewerjava.databinding.ActivityMainBinding;
import com.example.reviewerjava.ui.view.ReviewListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReviewListFragment reviewListFragment = new ReviewListFragment();
        FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
        fragmentManager
                .add(binding.fragmentContainerView.getId(), reviewListFragment)
                .addToBackStack(null)
                .commit();
    }
}