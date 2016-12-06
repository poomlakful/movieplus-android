package com.example.comsci.movieplus.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;

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
        return mTimeList.size();
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
        Button btn;
        if(view == null) {
            btn = new Button(mContext);
        } else {
            btn = (Button) view;
        }
        btn.setText(mTimeList.get(i));
        btn.setWidth();
        return btn;
    }
}
