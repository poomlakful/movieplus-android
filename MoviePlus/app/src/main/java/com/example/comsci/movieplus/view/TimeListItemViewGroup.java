package com.example.comsci.movieplus.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.comsci.movieplus.R;

/**
 * Created by comsci on 12/7/2016.
 */

public class TimeListItemViewGroup extends FrameLayout {
    private TextView tvTimeList;

    public TimeListItemViewGroup(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public TimeListItemViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
    }

    public TimeListItemViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TimeListItemViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
    }

    private void initInflate() {
        inflate(getContext(), R.layout.viewgroup_time_list_item,this);
    }

    private void initInstances() {
        tvTimeList = (TextView) findViewById(R.id.tvTimeList);
    }

    public void setTime(String text) {
        tvTimeList.setText(text);
    }
}
