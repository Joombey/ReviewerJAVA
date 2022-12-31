package com.example.reviewerjava.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.retrofit.OAuth2;
import com.example.reviewerjava.databinding.OAuthVkFragmentBinding;
import com.example.reviewerjava.presentation.viewmodel.OAuthVkViewModel;

public class OAuthVkFragment extends Fragment {
    private OAuthVkViewModel viewModel;
    private OAuthVkFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = OAuthVkFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(OAuthVkViewModel.class);
        CookieManager.getInstance().removeAllCookies(null);
        binding.webView.clearCache(true);
        binding.webView.loadUrl(OAuth2.AUTH_URL);
        binding.webView.setWebViewClient(
                viewModel.getClient(
                        getActivity().getExternalMediaDirs()[0],
                        ((MainActivity) getActivity())::replaceCurrentFragment
                )
        );
        super.onViewCreated(view, savedInstanceState);
    }
}
