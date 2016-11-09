package com.example.comsci.movieplus.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.comsci.movieplus.R;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initInstances();
    }

    private void initInstances() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Movie Detail");
    }
}
