package com.example.reviewerjava;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.example.reviewerjava.databinding.ActivityMainBinding;
import com.example.reviewerjava.ui.view.ReviewListFragment;

import java.util.function.ToDoubleBiFunction;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setFragment(new ReviewListFragment());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addBtn){
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("onCreate", "destroy");
    }

    public <T extends Fragment> void setFragment(T fragment){
        Log.i("onCreate", "adding");
        FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
        fragmentManager
                .setReorderingAllowed(true)
                .replace(binding.fragmentContainerView.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }

    public ActivityMainBinding getBinding() {
        return binding;
    }

    public <T extends Fragment> void remove(T fragment){
        Log.i("onCreate", "removing");
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }
}