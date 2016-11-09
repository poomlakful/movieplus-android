package com.example.comsci.movieplus.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comsci.movieplus.R;

/**
 * Created by comsci on 9/11/2559.
 */

public class HomeAdapter extends BaseAdapter {
    private Context mContext;

    public HomeAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return 20;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = null;
        ImageView imageView = null;
        if(convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.viewgroup_movie_item, parent, false);
            textView = (TextView) convertView.findViewById(R.id.tvMovieItemName);
            imageView = (ImageView) convertView.findViewById(R.id.ivMovieItemImage);
        }
        try {
            textView.setText("Movie " + position);
            imageView.setBackgroundResource(R.drawable.poster);
        } catch (NullPointerException e) {}
        return convertView;

    }
}
