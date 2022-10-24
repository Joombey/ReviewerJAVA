package com.example.reviewerjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.databinding.ActivityMainBinding;
import com.example.reviewerjava.ui.view.AddReviewFragment;
import com.example.reviewerjava.ui.view.RegisterFragment;
import com.example.reviewerjava.ui.view.ReviewFragment;
import com.example.reviewerjava.ui.view.ReviewListFragment;
import com.example.reviewerjava.ui.viewmodel.RegisterViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private RegisterViewModel mViewModel;
    private boolean loggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RepositoryController.init(getApplication());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        mViewModel.getRepository().observe(this, aBoolean -> {
            loggedIn = mViewModel.getRepository().getValue();
            if (aBoolean) getSupportActionBar().setTitle("Admin");
            else getSupportActionBar().setTitle("ReviewerJAVA");
        });
        Uri content = getIntent().getData();
        if(content != null){

            String[] parts = content.toString().split("/");
            Integer i = Integer.valueOf(parts[parts.length - 1]);
            setFragment(new ReviewFragment(), i);

        } else setFragment(new ReviewListFragment());
        Log.i("cacheDir", getCacheDir().toString());
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.addBtn:
                if(loggedIn){
                    setFragment(new AddReviewFragment());
                } else Toast.makeText(this, "Log In first", Toast.LENGTH_SHORT).show();
                break;
            case R.id.signIn:
                if(!loggedIn) setFragment(new RegisterFragment());
                else mViewModel.logOut();
                break;
            case android.R.id.home:
                popBackStack();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public <T extends Fragment> void setFragment(T fragment){
        FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
        fragmentManager
                .setReorderingAllowed(true)
                .replace(binding.fragmentContainerView.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }

    public <T extends Fragment> void setFragment(T fragment, int reviewId){
        Bundle bundle = new Bundle();
        bundle.putInt("reviewId", reviewId);
        fragment.setArguments(bundle);
        setFragment(fragment);
    }

    public <T extends Fragment> void setFragment(T fragment, int reviewId, int position){
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putInt("reviewId", reviewId);
        fragment.setArguments(bundle);
        setFragment(fragment);
    }

    public void popBackStack(){
        if(getSupportFragmentManager().getBackStackEntryCount() != 1) getSupportFragmentManager().popBackStack();
        else finish();
    }
}