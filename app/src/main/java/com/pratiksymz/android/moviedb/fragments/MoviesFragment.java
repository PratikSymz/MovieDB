package com.pratiksymz.android.moviedb.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pratiksymz.android.moviedb.R;
import com.pratiksymz.android.moviedb.adapters.SectionAdapter;
import com.pratiksymz.android.moviedb.models.Categories;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    /*
     * Tag for the log messages
     */
    public static final String LOG_TAG = MoviesFragment.class.getSimpleName();

    /**
     * Context of the parent activity
     */
    private Context mContext;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        // Get the context
        mContext = getActivity();

        // Find a reference to the {@link RecyclerView} in the layout
        final RecyclerView sectionsRecyclerView = rootView.findViewById(R.id.list_view_section_items);
        sectionsRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        sectionsRecyclerView.setHasFixedSize(true);

        List<Categories> categoriesAndSections = new ArrayList<>();
        categoriesAndSections.add(new Categories("movie", "popular"));
        categoriesAndSections.add(new Categories("movie", "top_rated"));

        SectionAdapter adapter = new SectionAdapter(mContext, categoriesAndSections);
        sectionsRecyclerView.setAdapter(adapter);

        // Return the fragment rootView
        return rootView;
    }
}
