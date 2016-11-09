package com.example.comsci.movieplus.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.comsci.movieplus.R;
import com.example.comsci.movieplus.dao.MovieItemDao;
import com.example.comsci.movieplus.manager.HttpManager;
import com.example.comsci.movieplus.manager.UtilityManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {
    TextView tvMovieDetailName;
    WebView wvMovieDetail;
    ImageView ivMovieDetail;
    TextView tvMovieDetailDirector;
    TextView tvMovieDetailType;
    TextView tvMovieDetailTime;
    TextView tvMovieDetailStatus;
    TextView tvMovieDetailDetail;

    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initInstances();
        fetchData();
    }

    private void fetchData() {
        Call<MovieItemDao> call = HttpManager.getInstance().getService().getMovieById(movieId);
        call.enqueue(new Callback<MovieItemDao>() {
            @Override
            public void onResponse(Call<MovieItemDao> call, Response<MovieItemDao> response) {
                MovieItemDao movieItemDao = response.body();
                if(movieItemDao == null) {
                    Toast.makeText(MovieDetailActivity.this,"Sory, No data to show.",Toast.LENGTH_SHORT).show();
                    return;
                }
                tvMovieDetailName.setText(movieItemDao.getName());
                tvMovieDetailDirector.setText(movieItemDao.getDirector());
                tvMovieDetailType.setText(movieItemDao.getType());
                tvMovieDetailTime.setText(movieItemDao.getTime()+ " min");
                tvMovieDetailStatus.setText(movieItemDao.getStatus());
                tvMovieDetailDetail.setText(movieItemDao.getDetail());
                Glide.with(MovieDetailActivity.this)
                        .load(movieItemDao.getPoster())
                        .placeholder(R.drawable.gray_image)
                        .into(ivMovieDetail);
                //wvMovieDetail.loadData(UtilityManager.getInstance().getTrailerHtml(movieItemDao.getTrailer()), "text/html", null);
            }

            @Override
            public void onFailure(Call<MovieItemDao> call, Throwable t) {
                Toast.makeText(MovieDetailActivity.this,t+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initInstances() {
        //Set action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        Intent intent = getIntent();
        movieId = intent.getIntExtra("id",0);

        tvMovieDetailName = (TextView) findViewById(R.id.tvMovieDetailName);
        wvMovieDetail = (WebView) findViewById(R.id.wvMovieDetail);
        ivMovieDetail = (ImageView) findViewById(R.id.ivMovieDetail);
        tvMovieDetailDirector = (TextView) findViewById(R.id.tvMovieDetailDirector);
        tvMovieDetailType = (TextView) findViewById(R.id.tvMovieDetailType);
        tvMovieDetailTime = (TextView) findViewById(R.id.tvMovieDetailTime);
        tvMovieDetailStatus = (TextView) findViewById(R.id.tvMovieDetailStatus);
        tvMovieDetailDetail = (TextView) findViewById(R.id.tvMovieDetailDetail);
    }
}
