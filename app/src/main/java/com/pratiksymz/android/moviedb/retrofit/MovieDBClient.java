package com.pratiksymz.android.moviedb.retrofit;

import com.pratiksymz.android.moviedb.models.MovieCellList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDBClient {
    @GET("{section}")
    Call<MovieCellList> movieCells (
            @Path("section") String section,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
