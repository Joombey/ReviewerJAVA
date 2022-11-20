package com.example.reviewerjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
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

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;
import com.example.reviewerjava.databinding.ActivityMainBinding;
import com.example.reviewerjava.ui.view.AddReviewFragment;
import com.example.reviewerjava.ui.view.ProfileFragment;
import com.example.reviewerjava.ui.view.RegisterFragment;
import com.example.reviewerjava.ui.view.ReviewFragment;
import com.example.reviewerjava.ui.view.ReviewListFragment;
import com.example.reviewerjava.ui.viewmodel.NavigationViewModel;
import com.example.reviewerjava.ui.viewmodel.RegisterViewModel;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavigationViewModel mViewModel;
    private UserAndPermission user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        mViewModel = new ViewModelProvider(this).get(NavigationViewModel.class);
        mViewModel.getCurrentUser().observe(this, user -> {
            this.user = user;
            changeUI();
        });
        setContentView(binding.getRoot());
        RepositoryController.init(getApplication());
        checkForDeppLink();
        binding.bottomNavigationView.setOnItemSelectedListener(this::onOptionsItemSelected);
    }

    private void checkForDeppLink(){
        Uri content = getIntent().getData();
        if(content != null){
            String[] parts = content.toString().split("/");
            Integer i = Integer.valueOf(parts[parts.length - 1]);
            setFragment(new ReviewFragment(), i);

        } else setFragment(new ReviewListFragment());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.reviewBlockView:
                setFragment(null);
                break;
            case R.id.userBanView:
                setFragment(null);
                break;
            case R.id.reviewMaker:
                setFragment(new AddReviewFragment());
                break;
            case R.id.profile:
                if(user.user != null)  setFragment(new ProfileFragment(user));
                else setFragment(new RegisterFragment());
                break;
            case R.id.roleChanger:
                setFragment(null);
                break;
            case android.R.id.home:
                popBackStack();
                break;
            default: break;
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

    public void changeUI(){
        binding.bottomNavigationView.getMenu()
                .findItem(R.id.reviewMaker)
                .setVisible(user.permission.reviewMakerAccess);
        binding.bottomNavigationView.getMenu()
                .findItem(R.id.profile)
                .setVisible(user.permission.profileAccess);
        binding.bottomNavigationView.getMenu()
                .findItem(R.id.reviewBlockView)
                .setVisible(user.permission.reviewBlockAccess);
        binding.bottomNavigationView.getMenu()
                .findItem(R.id.userBanView)
                .setVisible(user.permission.userBanAccess);
    }
}