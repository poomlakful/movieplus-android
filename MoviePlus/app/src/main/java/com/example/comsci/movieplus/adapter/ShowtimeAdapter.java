package com.example.comsci.movieplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.comsci.movieplus.R;
import com.example.comsci.movieplus.dao.ShowtimeItemDao;

import java.util.List;

/**
 * Created by comsci on 9/11/2559.
 */

public class ShowtimeAdapter extends BaseAdapter {
    Context mContext;
    List<ShowtimeItemDao> mShowtimeList;

    public ShowtimeAdapter(Context context, List<ShowtimeItemDao> st) {
        this.mContext= context;
        this.mShowtimeList = st;
    }

    public int getCount() {
        return mShowtimeList.size();
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
            convertView = mInflater.inflate(R.layout.viewgroup_showtime_item, parent, false);
        }
        TextView tvTheatreName = (TextView) convertView.findViewById(R.id.tvTheatreName);
        TextView tvMovieName = (TextView) convertView.findViewById(R.id.tvMovieName);
        TextView tvDirector = (TextView) convertView.findViewById(R.id.tvDirector);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        TextView tvShowtime = (TextView) convertView.findViewById(R.id.tvShowtime);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivPoster);
        try {
            tvTheatreName.setText(mShowtimeList.get(position).getTheatreName());
            tvMovieName.setText(mShowtimeList.get(position).getName());
            tvDirector.setText(mShowtimeList.get(position).getDirector());
            tvTime.setText(mShowtimeList.get(position).getTime()+" minute");
            List<String> showtime = mShowtimeList.get(position).getShowTime();
            String showtimeText = "";
            for(int i = 0; i<showtime.size();i++) showtimeText += showtime.get(i) + " ";
            tvShowtime.setText(showtimeText);
            Glide.with(mContext)
                    .load(mShowtimeList.get(position).getPoster())
                    .placeholder(R.drawable.poster)
                    .into(imageView);
        } catch (NullPointerException e) {}
        return convertView;
    }
}
