package com.example.rb_shibe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rb_shibe.databinding.ItemLayoutBinding;

import java.util.List;

public class ShibeAdapter extends RecyclerView.Adapter<ShibeAdapter.ViewHolder> {

    private List<String> urls;

    public ShibeAdapter(List<String> urls) {
        this.urls = urls;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemLayoutBinding binding;

        public  ViewHolder(ItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setUrl(String url) {
//            binding.tvDisplay.setText(url);

            Glide.with(this.itemView).load(url).into(binding.imageView);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding binding = ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = urls.get(position);
        holder.setUrl(url);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }
}
