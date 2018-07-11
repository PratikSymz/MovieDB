package com.pratiksymz.android.moviedb.models;

/**
 * Created by pratiksymz on 04/07/18.
 */

public class Categories {
    private String category;
    private String section;

    public Categories(String category, String section) {
        this.category = category;
        this.section = section;
    }

    public String getCategory() {
        return category;
    }

    public String getSection() {
        return section;
    }
}
