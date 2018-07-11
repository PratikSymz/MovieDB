package com.pratiksymz.android.moviedb;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.pratiksymz.android.moviedb.fragments.MoviesFragment;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_new);

        loadFragment(new MoviesFragment());

        final float startSize = 14f;
        final float endSize = 18f;
        long animationDuration = 100;

        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
        animator.setDuration(animationDuration);

        TextView mMoviesButton = findViewById(R.id.tv_shows_fragment_button);
        TextView mMoviesUnderline = findViewById(R.id.tv_shows_underline);

        animator.addUpdateListener(valueAnimator -> {
            float animatedValue = (float) valueAnimator.getAnimatedValue();
            mMoviesButton.setTextSize(animatedValue);
        });

        mMoviesButton.setOnClickListener(view -> {
            animator.start();
            mMoviesButton.setTextColor(getColor(R.color.black));
            mMoviesUnderline.setVisibility(View.VISIBLE);
        });



//        // Find the tab layout that shows the tabs
//        TabLayout mCategoryTab = findViewById(R.id.category_tab);
//        // Set the tab gravity
//        mCategoryTab.setTabGravity(TabLayout.GRAVITY_CENTER);
//
//        // Find the view pager that will allow the user to swipe between fragments
//        ViewPager mCategoryViewpager = findViewById(R.id.viewpager);
//
//        // Create an adapter that knows which fragment should be shown on each page
//        CategoryPagerAdapter pagerAdapter = new CategoryPagerAdapter(
//                getSupportFragmentManager(), this
//        );
//
//        // Set the adapter on to the viewpager
//        mCategoryViewpager.setAdapter(pagerAdapter);
//
//        // Connect the tab layout with the view pager. This will
//        //   1. Update the tab layout when the view pager is swiped
//        //   2. Update the view pager when a tab is selected
//        //   3. Set the tab layout's tab names with the view pager's adapter's titles
//        //      by calling onPageTitle()
//        mCategoryTab.setupWithViewPager(mCategoryViewpager);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
