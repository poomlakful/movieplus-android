package com.example.comsci.movieplus.manager;

import android.content.Context;

/**
 * Created by comsci on 9/11/2559.
 */

public class UtilityManager {
    private static UtilityManager instance;
    private Context mContext;

    public static UtilityManager getInstance() {
        if (instance == null)
            instance = new UtilityManager();
        return instance;
    }

    public String getTrailerHtml(String url) {
        String html =  "<iframe width=\"420\" height=\"315\" src=\""+url+"\"></iframe>\"";
        return html;
    }
}
