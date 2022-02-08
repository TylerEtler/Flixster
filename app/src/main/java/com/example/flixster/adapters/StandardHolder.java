package com.example.flixster.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.R;
import com.example.flixster.databinding.ItemMovieBinding;

public class StandardHolder extends RecyclerView.ViewHolder {

    TextView tvTitle;
    TextView tvOverview;
    ImageView ivPoster;
    RelativeLayout container;
    final ItemMovieBinding binding;

    public StandardHolder(@NonNull View itemView) {
        super(itemView);
        binding = ItemMovieBinding.bind(itemView);
        tvTitle = binding.tvTitle;
        tvOverview = binding.tvOverview;
        ivPoster = binding.ivPoster;
        container = itemView.findViewById(R.id.standard_container);
    }

    public RelativeLayout getContainer() {
        return container;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getTvOverview() {
        return tvOverview;
    }

    public ImageView getIvPoster() {
        return ivPoster;
    }
}
