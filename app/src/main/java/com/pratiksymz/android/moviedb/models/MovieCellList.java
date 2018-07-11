package com.pratiksymz.android.moviedb.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieCellList {
    @SerializedName("results")
    List<MovieCell> movieCells;

    public List<MovieCell> getMovieCells() {
        return movieCells;
    }
}
