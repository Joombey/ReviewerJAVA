package com.example.reviewerjava.presentation.view.adapter;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.databinding.RoleChangerElementBinding;
import com.example.reviewerjava.utils.Adminer;

import java.util.List;

public class RoleChangerAdapter extends RecyclerView.Adapter<RoleChangerAdapter.RoleChangerViewHolder> {
    private final List<UserEntity> data;
    private final Adminer adminer;

    public RoleChangerAdapter(List<UserEntity> data, Adminer adminer) {
        this.data = data;
        this.adminer = adminer;
    }

    @NonNull
    @Override
    public RoleChangerAdapter.RoleChangerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RoleChangerElementBinding binding = RoleChangerElementBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new RoleChangerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoleChangerAdapter.RoleChangerViewHolder holder, int position) {
        holder.binding.banUserBtn.setOnClickListener(view -> {
            adminer.ban(data.get(position));
        });

        holder.binding.makeModeratorBtn.setOnClickListener(view -> {
            adminer.updateUser(data.get(position), UserEntity.MODERATOR);
        });

        holder.binding.makeUserBtn.setOnClickListener(view -> {
            adminer.updateUser(data.get(position), UserEntity.USER);
        });

        holder.binding.userLayout.setOnClickListener(view -> {
            adminer.checkUser(data.get(position));
        });

        holder.binding.userName.setText(data.get(position).getName());
        holder.binding.userCity.setText(data.get(position).getCity());
        if(data.get(position).getAvatar() != null) {
            holder.binding.userImage.setImageBitmap(
                    BitmapFactory.decodeFile(
                            Uri.parse(data.get(position).getAvatar()).getPath()
                    )
            );
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RoleChangerViewHolder extends RecyclerView.ViewHolder {
        private final RoleChangerElementBinding binding;

        public RoleChangerViewHolder(RoleChangerElementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
