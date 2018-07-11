package com.pratiksymz.android.moviedb.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pratiksymz.android.moviedb.R;
import com.pratiksymz.android.moviedb.models.Categories;
import com.pratiksymz.android.moviedb.models.MovieCell;
import com.pratiksymz.android.moviedb.models.MovieCellList;
import com.pratiksymz.android.moviedb.retrofit.MovieDBClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {
    private Context mContext;
    private List<Categories> mCategoryAndSectionList;

    /*
     * Tag for the log messages
     */
    public static final String LOG_TAG = SectionAdapter.class.getSimpleName();

    /**
     * Adapter Constructor
     * @param context
     * @param categoryAndSectionList
     */
    public SectionAdapter(Context context, List<Categories> categoryAndSectionList) {
        mContext = context;
        mCategoryAndSectionList = categoryAndSectionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View itemView = layoutInflater.inflate(R.layout.layout_section, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        // Get the current section header
        Categories currentCategoryAndSection = mCategoryAndSectionList.get(position);

        // Set the section header text
        TextView sectionHeader = viewHolder.viewSectionHeader;
        switch (currentCategoryAndSection.getSection()) {
            case "popular":
                sectionHeader.setText("Popular");
                break;

            case "top_rated":
                sectionHeader.setText("Top Rated");
        }

        // Fetch and set the values of the section
        // Build the Retrofit Client
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/" + currentCategoryAndSection.getCategory() + "/")
                .addConverterFactory(GsonConverterFactory.create());

        // Instantiate Retrofit
        Retrofit retrofit = retrofitBuilder.build();

        // Initialize the client
        MovieDBClient movieDBClient = retrofit.create(MovieDBClient.class);
        String apiKey = "ca6b45d29d9793a42dcc338999613434";

        // Create the Call to the API
        Call<MovieCellList> call = movieDBClient.movieCells(
                currentCategoryAndSection.getSection(),
                apiKey,
                "en-US"
        );

        Log.d(LOG_TAG, call.request().toString());

        final RecyclerView sectionItemsView = viewHolder.sectionItemsRecyclerView;
        sectionItemsView.setLayoutManager(
                new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        );

        // Asynchronous Request to MovieDB API
        call.enqueue(new Callback<MovieCellList>() {
            @Override
            public void onResponse(Call<MovieCellList> call, Response<MovieCellList> response) {
                List<MovieCell> movieCellList = response.body().getMovieCells();

                Log.d(LOG_TAG, String.valueOf(movieCellList.size()));

                // Create a new adapter that takes the list of movie cells as input
                MovieCellAdapter adapter = new MovieCellAdapter(mContext, movieCellList);
                // Set the adapter on the {@link recyclerView}
                // so the list can be populated in the user interface
                sectionItemsView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieCellList> call, Throwable t) {
                Toast.makeText(mContext, "Something went wrong!", Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoryAndSectionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView viewSectionHeader;
        private RecyclerView sectionItemsRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            viewSectionHeader = itemView.findViewById(R.id.header_section);
            sectionItemsRecyclerView = itemView.findViewById(R.id.recycler_view_section_items);
        }
    }
}
