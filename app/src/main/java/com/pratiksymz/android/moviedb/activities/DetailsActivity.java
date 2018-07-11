package com.pratiksymz.android.moviedb.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pratiksymz.android.moviedb.R;
import com.pratiksymz.android.moviedb.models.MovieCell;

import java.text.DecimalFormat;

public class DetailsActivity extends AppCompatActivity {
    private MovieCell mMovieCell;

    private static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w342/";
    private static final String BACKDROP_BASE_URL = "http://image.tmdb.org/t/p/w1280/";

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");
    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("#,##,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get the clicked item data
        mMovieCell = (MovieCell) getIntent().getSerializableExtra("movie");

        // Set the backdrop
        ImageView mBackdropView = findViewById(R.id.movie_detail_backdrop);
        if (!mMovieCell.getBackdropUrl().isEmpty()) {
            Glide.with(this).load(BACKDROP_BASE_URL + mMovieCell.getBackdropUrl()).into(mBackdropView);
        }

        // Set the poster
        ImageView mPosterView = findViewById(R.id.movie_detail_poster);
        if (!mMovieCell.getPosterUrl().isEmpty()) {
            Glide.with(this).load(POSTER_BASE_URL + mMovieCell.getPosterUrl()).into(mPosterView);
        }

        // Set the title
        TextView mTitleView = findViewById(R.id.movie_detail_title);
        if (!mMovieCell.getTitle().isEmpty()) {
            mTitleView.setText(mMovieCell.getTitle());
        }

        // Set the average votes
        TextView mAvgVotesView = findViewById(R.id.movie_detail_vote_avg);
        mAvgVotesView.setText(String.valueOf(DECIMAL_FORMAT.format(mMovieCell.getVoteAverage())));

        // Set the popularity
        TextView mPopularityView = findViewById(R.id.movie_detail_popularity);
        mPopularityView.setText(String.valueOf(Math.round(mMovieCell.getPopularity())) + "%");

        // Set the the number of votes
        TextView mVoteCountView = findViewById(R.id.movie_detail_vote_count);
        mVoteCountView.setText(String.valueOf(NUMBER_FORMAT.format(mMovieCell.getVoteCount())));
    }
}
