package com.example.comsci.movieplus.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.comsci.movieplus.R;
import com.example.comsci.movieplus.dao.MovieItemDao;

import java.util.List;

/**
 * Created by comsci on 9/11/2559.
 */

public class HomeAdapter extends BaseAdapter {
    private Context mContext;
    private List<MovieItemDao> mMovieList;

    public HomeAdapter(Context c, List<MovieItemDao> m) {
        mContext = c;
        mMovieList = m;
    }

    public int getCount() {
        return mMovieList == null ? 0 : mMovieList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.viewgroup_movie_item, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tvMovieItemName);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivMovieItemImage);
        try {
            textView.setText(mMovieList.get(position).getName());
            Glide.with(mContext)
                    .load(mMovieList.get(position).getPoster())
                    .placeholder(R.drawable.gray_image)
                    .into(imageView);
        } catch (NullPointerException e) {}
        return convertView;

    }
}
