package com.example.flixster.adapters;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.R;

public class PopularHolder extends RecyclerView.ViewHolder {

    private ImageView ivPoster;

    public PopularHolder(@NonNull View itemView) {
        super(itemView);
        ivPoster = itemView.findViewById(R.id.ivPoster);
    }

    public ImageView getIvPoster() {
        return ivPoster;
    }
}
