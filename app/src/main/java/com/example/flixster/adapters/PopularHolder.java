package com.example.flixster.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.R;
import com.example.flixster.databinding.ItemPopularMovieBinding;

public class PopularHolder extends RecyclerView.ViewHolder {

    private ImageView ivPoster;
    private ImageView overlay;
    RelativeLayout container;
    final ItemPopularMovieBinding binding;

    public PopularHolder(@NonNull View itemView) {
        super(itemView);
        binding = ItemPopularMovieBinding.bind(itemView);

        ivPoster = binding.ivPoster;
        overlay = binding.overlay;
        container = itemView.findViewById(R.id.popular_container);
    }

    public RelativeLayout getContainer() {
        return container;
    }

    public ImageView getIvPoster() {
        return ivPoster;
    }

    public ImageView getOverlay() {
        return overlay;
    }
}
