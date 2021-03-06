package com.example.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailActivity;
import com.example.flixster.MainActivity;
import com.example.flixster.PopularDetailActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Movie> movies;

    private final int POPULAR = 1, STANDARD = 0;

    private Activity activity;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    @Override
    public int getItemViewType(int position) {
        Log.d("MovieAdapter", "Rating: " + movies.get(position).getRating());
        if (movies.get(position).getRating() >= 7) {
            return POPULAR;
        }
        else {
            return STANDARD;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView;
        RecyclerView.ViewHolder newViewHolder;
        switch (viewType) {
            case POPULAR:
                movieView = LayoutInflater.from(context).inflate(R.layout.item_popular_movie, parent, false);
                newViewHolder = new PopularHolder(movieView);
                break;
            case STANDARD:
            default:
                movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
                newViewHolder = new StandardHolder(movieView);
                break;
        }
        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);
        switch(holder.getItemViewType()) {
            case POPULAR:
                PopularHolder holder1 = (PopularHolder) holder;
                configurePopularHolder(holder1, position);
                break;
            case STANDARD:
            default:
                StandardHolder holder2 = (StandardHolder) holder;
                configureStandardHolder(holder2, position);
                break;
        }
        //Movie movie = movies.get(position);
        //holder.bind(movie);
    }

    private void configurePopularHolder(PopularHolder holder, int position) {
        String imageUrl = movies.get(position).getBackdropPath();
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholderlandscape)
                .override(3000, 0)
                .into(holder.getIvPoster());

        Glide.with(context)
                .load(R.drawable.playover)
                .override(3000, 0)
                .fitCenter()
                .into(holder.getOverlay());

        holder.getContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, movies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, PopularDetailActivity.class);
                i.putExtra("movie", Parcels.wrap(movies.get(position)));
                context.startActivity(i);
            }
        });
    }

    private void configureStandardHolder(StandardHolder holder, int position) {
        holder.binding.setMovie(movies.get(position));
        holder.binding.executePendingBindings();
        //holder.getTvTitle().setText(movies.get(position).getTitle());
        //holder.getTvOverview().setText(movies.get(position).getOverview());
        String imageUrl;
        int placeholder;
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUrl = movies.get(position).getBackdropPath();
            placeholder = R.drawable.placeholderlandscape;
        }
        else {
            imageUrl = movies.get(position).getPosterPath();
            placeholder = R.drawable.placeholderportrait;
        }

        Glide.with(context)
                .load(imageUrl)
                .placeholder(placeholder)
                .transform(new RoundedCornersTransformation(30, 0))
                .into(holder.getIvPoster());

        holder.getContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, movies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("movie", Parcels.wrap(movies.get(position)));
                Pair<View, String> titlePair = Pair.create((View)holder.binding.tvTitle, "title");
                Pair<View, String> overviewPair = Pair.create((View)holder.binding.tvOverview, "overview");
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, titlePair, overviewPair);
                context.startActivity(i, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /*
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            }
            else {
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholderportrait)
                    .into(ivPoster);

        }
    }
     */
}
