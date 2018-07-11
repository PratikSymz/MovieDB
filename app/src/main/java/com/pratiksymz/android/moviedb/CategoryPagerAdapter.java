package com.pratiksymz.android.moviedb;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pratiksymz.android.moviedb.fragments.MoviesFragment;

public class CategoryPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    // Public constructor for the ViewPager Adapter
    public CategoryPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // Set the Fragments as ViewPager items
        // And else is required!!
        if (position == 0) {
            return new MoviesFragment();
        } else {
            return new Fragment();
        }
    }

    @Override
    public int getCount() {
        // Returns the number of items of the Viewpager
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // Set the titles of the tabs for the TabLayout
        if (position == 0) {
            return "Movies";
        } else {
            return "Others";
        }
    }
}
