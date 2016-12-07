package com.example.comsci.movieplus.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.comsci.movieplus.R;
import com.example.comsci.movieplus.dao.ShowtimeItemDao;
import com.example.comsci.movieplus.view.MyGridView;
import com.example.comsci.movieplus.view.ShowtimeItemViewGroup;

import java.util.List;

/**
 * Created by comsci on 9/11/2559.
 */

public class ShowtimeAdapter extends BaseAdapter {
    private Context mContext;
    private String mCinemaName;
    private List<ShowtimeItemDao> mShowtimeList;

    public ShowtimeAdapter(Context context, List<ShowtimeItemDao> st, String cinemaName) {
        this.mContext = context;
        this.mShowtimeList = st;
        this.mCinemaName = cinemaName;
    }

    public int getCount() {
        return mShowtimeList == null ? 0 : mShowtimeList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ShowtimeItemViewGroup item;
        if(convertView == null) {
            item = new ShowtimeItemViewGroup(mContext);
        } else {
            item = (ShowtimeItemViewGroup) convertView;
        }
        try {
            ShowtimeItemDao dao = mShowtimeList.get(position);
            item.setMovieID(dao.getMovieID());
            item.setTheatreName(dao.getTheatreName());
            item.setCinemaName(mCinemaName);
            item.setMovieName(dao.getName());
            item.setDirector(dao.getDirector());
            item.setTime(dao.getTime());
            item.setPosterImage(dao.getPoster());
            item.setTimeList(dao.getShowTime());
        } catch (NullPointerException e) {}
        return item;
    }
}
