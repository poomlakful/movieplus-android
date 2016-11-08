package com.example.comsci.movieplus.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        TextView textView;
        if(convertView == null){
            textView = new TextView(mContext);
        }
        else{
            textView = (TextView) convertView;
        }
        textView.setText("Position "+position);
        return textView;

    }
}
