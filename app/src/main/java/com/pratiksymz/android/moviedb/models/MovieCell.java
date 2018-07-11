package com.pratiksymz.android.moviedb.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovieCell implements Serializable {
    @SerializedName("id")
    long id;

    @SerializedName("title")
    String title;

    @SerializedName("poster_path")
    String posterUrl;

    @SerializedName("backdrop_path")
    String backdropUrl;

    @SerializedName("release_date")
    String releaseDate;

    @SerializedName("popularity")
    double popularity;

    @SerializedName("vote_count")
    int voteCount;

    @SerializedName("vote_average")
    double voteAverage;

    @SerializedName("genre_ids")
    List<Integer> genreIds;

    /**
     * Getters
     */
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

}
