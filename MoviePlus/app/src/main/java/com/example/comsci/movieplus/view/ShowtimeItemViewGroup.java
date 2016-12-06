package com.example.comsci.movieplus.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.comsci.movieplus.R;
import com.example.comsci.movieplus.adapter.TimeListAdapter;

import java.util.List;

/**
 * Created by comsci on 12/6/2016.
 */

public class ShowtimeItemViewGroup extends FrameLayout {

    private TextView tvTheatreName;
    private TextView tvMovieName;
    private TextView tvDirector;
    private TextView tvTime;
    private ImageView imageView;
    private MyGridView gvTimeList;

    public ShowtimeItemViewGroup(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public ShowtimeItemViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
    }

    public ShowtimeItemViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShowtimeItemViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
    }

    private void initInflate() {
        inflate(getContext(),R.layout.viewgroup_showtime_item,this);
    }

    private void initInstances() {
        tvTheatreName = (TextView) findViewById(R.id.tvTheatreName);
        tvMovieName = (TextView) findViewById(R.id.tvMovieName);
        tvDirector = (TextView) findViewById(R.id.tvDirector);
        tvTime = (TextView) findViewById(R.id.tvTime);
        imageView = (ImageView) findViewById(R.id.ivPoster);
        gvTimeList = (MyGridView) findViewById(R.id.gvTimeList);
    }

    public void setTheatreName(String text) {
        tvTheatreName.setText(text);
    }

    public void setMovieName(String text) {
        tvMovieName.setText(text);
    }

    public void setDirector(String text) {
        tvDirector.setText(text);
    }

    public void setTime(String text) {
        tvTime.setText(text+" minute");
    }

    public void setPosterImage(String url) {
        Glide.with(getContext())
                .load(url)
                .placeholder(R.drawable.gray_image)
                .into(imageView);
    }

    public void setTimeList(final List<String> timeList) {
        TimeListAdapter adapter = new TimeListAdapter(getContext(),timeList);
        gvTimeList.setAdapter(adapter);
        gvTimeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),timeList.get(i),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
