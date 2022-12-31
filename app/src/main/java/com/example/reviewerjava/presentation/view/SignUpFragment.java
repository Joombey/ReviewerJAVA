package com.example.reviewerjava.presentation.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.databinding.SignUpFragmentBinding;
import com.example.reviewerjava.presentation.viewmodel.SignUpViewModel;

public class SignUpFragment extends Fragment {
    private SignUpViewModel viewModel;
    private SignUpFragmentBinding binding;
    private String imagePath;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SignUpFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        MainActivity activity = ((MainActivity) getActivity());
        binding.userImage.setOnClickListener(v->{
            if (activity != null) {
                activity.getActivityResultRegistry().register("key",
                        new ActivityResultContracts.OpenDocument(), result -> {
                            activity.getApplicationContext()
                                    .getContentResolver()
                                    .takePersistableUriPermission(result, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            imagePath = result.getPath();
                            binding.userImage.setImageBitmap(
                                    BitmapFactory.decodeFile(imagePath)
                            );
                        }).launch(new String[]{"image/*"});
            }
        });

        binding.singUpButton.setOnClickListener(v->{
            if (binding.login.getText().toString().equals("")
                    || binding.password.getText().toString().equals("")
                    || binding.authorCity.getText().toString().equals("")){
                Toast.makeText(getContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show();
                return;
            }
//            if(viewModel.signUp(
//                    binding.login.getText().toString(),
//                    binding.password.getText().toString(),
//                    binding.authorCity.getText().toString(),
//                    imagePath)
//            ){
//                Toast.makeText(activity, "Вы успешно зарегестрировались", Toast.LENGTH_SHORT).show();
//            } else{
//                Toast.makeText(activity, "Такой пользователь существует", Toast.LENGTH_SHORT).show();
//            }

            viewModel.signUp(binding.login.getText().toString(),
                    binding.password.getText().toString(),
                    binding.authorCity.getText().toString(),
                    imagePath).observe(getViewLifecycleOwner(), aBoolean -> {
                        if (aBoolean != null){
                            if (aBoolean){
                                Toast.makeText(activity, "Вы успешно зарегестрировались", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(activity, "Такой пользователь существует", Toast.LENGTH_SHORT).show();
                            }
                        }
            });
        });
    }
}
