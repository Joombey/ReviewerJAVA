package com.example.reviewerjava.presentation.view.adapter;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.databinding.ImageSliderElementBinding;

import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>{

    private final List<String> pictureList;
    MainActivity activity;

    public ImageSliderAdapter(List<String> pictureList, boolean writeAccess, MainActivity activity){
        this.pictureList = pictureList;
        this.activity = activity;
        if (writeAccess == true){
            pictureList.add(null);
        }else{
            pictureList.remove(pictureList.size() - 1);
        }
    }

    @NonNull
    @Override
    public ImageSliderAdapter.ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageSliderElementBinding binding = ImageSliderElementBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ImageSliderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderAdapter.ImageSliderViewHolder holder, int position) {
        if (pictureList.get(position) == null) {
            holder.binding.image.setVisibility(View.GONE);
            holder.binding.addImageBtn.setVisibility(View.VISIBLE);
            holder.binding.addImageBtn.setOnClickListener((View v) -> {
                if (activity != null) {
                    activity.getActivityResultRegistry().register("key",
                            new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
                        @Override
                        public void onActivityResult(Uri result) {
                            activity
                                    .getApplicationContext()
                                    .getContentResolver()
                                    .takePersistableUriPermission(result, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            pictureList.add(pictureList.size() - 1, result.toString());
                            notifyDataSetChanged();
                        }
                    }).launch(new String[]{"image/*"});
                }
            });
        } else {
            holder.binding.addImageBtn.setVisibility(View.GONE);
            holder.binding.image.setVisibility(View.VISIBLE);
            if (activity != null) {
                try {
                    holder.binding.image.setImageBitmap(
                            BitmapFactory.decodeFileDescriptor(
                                    activity.getApplicationContext().getContentResolver().openFileDescriptor(
                                            Uri.parse(pictureList.get(position)), "r").getFileDescriptor()
                            )
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public class ImageSliderViewHolder extends RecyclerView.ViewHolder {
        private final ImageSliderElementBinding binding;
        public ImageSliderViewHolder(ImageSliderElementBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
