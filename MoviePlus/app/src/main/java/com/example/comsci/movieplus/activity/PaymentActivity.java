package com.example.comsci.movieplus.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.comsci.movieplus.R;
import com.example.comsci.movieplus.dao.MovieItemDao;
import com.example.comsci.movieplus.manager.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    TextView tvPaymentName;
    TextView tvPaymentDirector;
    TextView tvPaymentType;
    ImageView ivPaymentPoster;

    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initInstances();
        fetchData();
    }

    private void initInstances() {
        //Set action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("Payment");

        Intent intent = getIntent();
        movieId = intent.getIntExtra("id", 0);

        tvPaymentName = (TextView) findViewById(R.id.tvPaymentName) ;
        tvPaymentDirector = (TextView) findViewById(R.id.tvPaymentDirector);
        tvPaymentType = (TextView) findViewById(R.id.tvPaymentType);
        ivPaymentPoster = (ImageView) findViewById(R.id.ivPaymentPoster);
    }

    private void fetchData() {
        Call<MovieItemDao> call = HttpManager.getInstance().getService().getMovieById(movieId);
        call.enqueue(new Callback<MovieItemDao>() {
            @Override
            public void onResponse(Call<MovieItemDao> call, Response<MovieItemDao> response) {
                MovieItemDao movieItemDao = response.body();
                if (movieItemDao == null) {
                    Toast.makeText(PaymentActivity.this, "Sory, No data to show.", Toast.LENGTH_SHORT).show();
                    return;
                }
                tvPaymentName.setText(movieItemDao.getName());
                tvPaymentDirector.setText(movieItemDao.getDirector());
                tvPaymentType.setText(movieItemDao.getType());
                Glide.with(PaymentActivity.this)
                        .load(movieItemDao.getPoster())
                        .placeholder(R.drawable.gray_image)
                        .into(ivPaymentPoster);
                //wvMovieDetail.loadData(UtilityManager.getInstance().getTrailerHtml(movieItemDao.getTrailer()), "text/html", null);
            }

            @Override
            public void onFailure(Call<MovieItemDao> call, Throwable t) {
                try {
                    Toast.makeText(PaymentActivity.this, t + "", Toast.LENGTH_SHORT).show();
                } catch (NullPointerException e) {}
            }
        });
    }
}
