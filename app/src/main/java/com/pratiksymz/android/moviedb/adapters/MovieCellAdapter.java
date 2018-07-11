package com.pratiksymz.android.moviedb.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pratiksymz.android.moviedb.R;
import com.pratiksymz.android.moviedb.activities.DetailsActivity;
import com.pratiksymz.android.moviedb.models.MovieCell;

import java.util.List;

public class MovieCellAdapter extends RecyclerView.Adapter<MovieCellAdapter.ViewHolder> {
    private Context mContext;
    private List<MovieCell> mListMovieCells;

    /*
     * Tag for the log messages
     */
    public static final String LOG_TAG = MovieCellAdapter.class.getSimpleName();

    /**
     * Adapter Constructor
     * @param context
     * @param movieCells
     */
    public MovieCellAdapter(Context context, List<MovieCell> movieCells) {
        mContext = context;
        mListMovieCells = movieCells;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = layoutInflater.inflate(R.layout.layout_movie_cell, viewGroup, false);

        // Return a new ViewHolder instance
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // Get the data model based on position
        MovieCell movieCell = mListMovieCells.get(position);

        String imageBaseUrl = "http://image.tmdb.org/t/p/w342/";

        ImageView movieCellPoster = viewHolder.cellPosterImage;
        TextView movieCellTitle = viewHolder.cellMovieTitle;

        // Set data to the views
        Glide.with(mContext).load(imageBaseUrl + movieCell.getPosterUrl()).into(movieCellPoster);
        movieCellTitle.setText(movieCell.getTitle());

        movieCellPoster.setOnClickListener(view -> {
            Intent detailsIntent = new Intent(mContext, DetailsActivity.class);

            // Send the MovieCell Object to the DetailsActivity
            detailsIntent.putExtra("movie", movieCell);
            mContext.startActivity(detailsIntent);
        });
    }

    @Override
    public int getItemCount() {
        return mListMovieCells.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cellPosterImage;
        private TextView cellMovieTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cellPosterImage = itemView.findViewById(R.id.movie_cell_poster_image);
            cellMovieTitle = itemView.findViewById(R.id.movie_cell_title);
        }
    }
}
