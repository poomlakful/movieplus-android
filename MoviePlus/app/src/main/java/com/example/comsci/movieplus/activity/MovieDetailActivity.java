package com.example.comsci.movieplus.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.comsci.movieplus.R;
import com.example.comsci.movieplus.dao.MovieItemDao;
import com.example.comsci.movieplus.fragment.HomeFragment;
import com.example.comsci.movieplus.manager.HttpManager;
import com.pierfrancescosoffritti.youtubeplayer.AbstractYouTubeListener;
import com.pierfrancescosoffritti.youtubeplayer.YouTubePlayerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {
    ProgressBar pbMovieDetail;
    LinearLayout llMovieDetail;
    TextView tvMovieDetailName;
    YouTubePlayerView youtubePlayer;
    ImageView ivMovieDetail;
    TextView tvMovieDetailDirector;
    TextView tvMovieDetailType;
    TextView tvMovieDetailTime;
    TextView tvMovieDetailStatus;
    TextView tvMovieDetailDetail;
    Button btBuyNow;

    int movieId;

    final int SHOWTIME_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initInstances();
        fetchData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        youtubePlayer.pauseVideo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youtubePlayer.release();
    }

    private void fetchData() {
        onFetchData(true);
        Call<MovieItemDao> call = HttpManager.getInstance().getService().getMovieById(movieId);
        call.enqueue(new Callback<MovieItemDao>() {
            @Override
            public void onResponse(Call<MovieItemDao> call, Response<MovieItemDao> response) {
                onFetchData(false);
                MovieItemDao movieItemDao = response.body();
                if (movieItemDao == null) {
                    Toast.makeText(MovieDetailActivity.this, "Sory, No data to show.", Toast.LENGTH_SHORT).show();
                    return;
                }
                setView(movieItemDao);
            }

            @Override
            public void onFailure(Call<MovieItemDao> call, Throwable t) {
                onFetchData(false);
                try {
                    Toast.makeText(MovieDetailActivity.this, t + "", Toast.LENGTH_SHORT).show();
                } catch (NullPointerException e) { }
            }
        });
    }

    private void setView(final MovieItemDao movieItemDao) {
        tvMovieDetailName.setText(movieItemDao.getName());
        tvMovieDetailDirector.setText(movieItemDao.getDirector());
        tvMovieDetailType.setText(movieItemDao.getType());
        tvMovieDetailTime.setText(movieItemDao.getTime() + " min");
        tvMovieDetailStatus.setText(movieItemDao.getStatus());
        tvMovieDetailDetail.setText(movieItemDao.getDetail());

        // set poster image
        Glide.with(MovieDetailActivity.this)
                .load(movieItemDao.getPoster())
                .placeholder(R.drawable.gray_image)
                .into(ivMovieDetail);

        // set trailer video
        youtubePlayer.initialize(new AbstractYouTubeListener() {
            @Override
            public void onReady() {
                youtubePlayer.loadVideo(movieItemDao.getTrailer(), 0);
            }
        }, true);
    }

    private void initInstances() {
        //Set action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("Movie Detail");

        Intent intent = getIntent();
        movieId = intent.getIntExtra("id", 0);

        pbMovieDetail = (ProgressBar) findViewById(R.id.pbMovieDetail);
        llMovieDetail = (LinearLayout) findViewById(R.id.llMovieDetail);
        tvMovieDetailName = (TextView) findViewById(R.id.tvMovieDetailName);
        youtubePlayer = (YouTubePlayerView) findViewById(R.id.vvMovieDetail);
        ivMovieDetail = (ImageView) findViewById(R.id.ivMovieDetail);
        tvMovieDetailDirector = (TextView) findViewById(R.id.tvMovieDetailDirector);
        tvMovieDetailType = (TextView) findViewById(R.id.tvMovieDetailType);
        tvMovieDetailTime = (TextView) findViewById(R.id.tvMovieDetailTime);
        tvMovieDetailStatus = (TextView) findViewById(R.id.tvMovieDetailStatus);
        tvMovieDetailDetail = (TextView) findViewById(R.id.tvMovieDetailDetail);
        btBuyNow = (Button) findViewById(R.id.btBuyNow);
        btBuyNow.setOnClickListener(btBuyNowListener);
    }

    private void onFetchData(boolean bool) {
        if(bool) {
            pbMovieDetail.setVisibility(View.VISIBLE);
            llMovieDetail.setVisibility(View.INVISIBLE);
            btBuyNow.setVisibility(View.INVISIBLE);
        } else {
            pbMovieDetail.setVisibility(View.INVISIBLE);
            llMovieDetail.setVisibility(View.VISIBLE);
            btBuyNow.setVisibility(View.VISIBLE);
        }
    }

    private View.OnClickListener btBuyNowListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setResult(SHOWTIME_CODE);
            finish();
        }
    };
}
