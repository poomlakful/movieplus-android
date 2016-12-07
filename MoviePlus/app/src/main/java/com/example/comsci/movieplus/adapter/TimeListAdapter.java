package com.example.comsci.movieplus.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.comsci.movieplus.view.TimeListItemViewGroup;

import java.util.List;

/**
 * Created by comsci on 12/7/2016.
 */

public class TimeListAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mTimeList;

    public TimeListAdapter(Context context, List<String> timeList) {
        mContext = context;
        mTimeList = timeList;
    }

    @Override
    public int getCount() {
        return mTimeList == null ? 0 : mTimeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TimeListItemViewGroup item;
        if(view == null) {
            item = new TimeListItemViewGroup(mContext);
        } else {
            item = (TimeListItemViewGroup) view;
        }
        item.setTime(mTimeList.get(i));
        return item;
    }
}
