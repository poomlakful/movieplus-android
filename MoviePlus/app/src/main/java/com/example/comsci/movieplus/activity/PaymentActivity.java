package com.example.comsci.movieplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
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
import com.example.comsci.movieplus.manager.HttpManager;

import org.w3c.dom.Text;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    private ProgressBar pbMovieDetail;
    private LinearLayout llMovieDetail;
    private TextView tvCinemaName;
    private TextView tvDate;
    private TextView tvTime;
    private TextView tvTheatreName;
    private TextView tvSeatName;
    private TextView tvTotalPrice;
    private TextView tvPaymentName;
    private TextView tvPaymentDirector;
    private TextView tvPaymentType;
    private ImageView ivPaymentPoster;
    private Button payment;

    private int mMovieId;
    private String mTheatreName;
    private String mCinemaName;
    private String mTime;
    private int mTotalPrice;
    private String mSeatName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initInstances();
        fetchData();
    }


    private void initInstances() {
        // Set action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("Payment");

        // get intent value
        Intent intent = getIntent();
        mMovieId = intent.getIntExtra("id", 0);
        mTime = intent.getStringExtra("time");
        mTheatreName = intent.getStringExtra("theatre");
        mTotalPrice = intent.getIntExtra("total", 0);
        mSeatName = intent.getStringExtra("seat");
        mCinemaName = intent.getStringExtra("cinema");

        // find view
        pbMovieDetail = (ProgressBar) findViewById(R.id.pbMovieDetail);
        llMovieDetail = (LinearLayout) findViewById(R.id.llMovieDetail);
        tvCinemaName = (TextView) findViewById(R.id.tvCinemaName);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvTheatreName = (TextView) findViewById(R.id.tvTheatreName);
        tvSeatName = (TextView) findViewById(R.id.tvSeatName);
        tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);
        tvPaymentName = (TextView) findViewById(R.id.tvPaymentName) ;
        tvPaymentDirector = (TextView) findViewById(R.id.tvPaymentDirector);
        tvPaymentType = (TextView) findViewById(R.id.tvPaymentType);
        ivPaymentPoster = (ImageView) findViewById(R.id.ivPaymentPoster);
        payment = (Button) findViewById(R.id.btnConfirm);

        // set view
        tvCinemaName.setText(mCinemaName);
        String date = DateFormat.format("yyyy/MM/dd", new Date()).toString();
        tvDate.setText(date);
        tvTime.setText(mTime);
        tvTheatreName.setText(mTheatreName);
        tvSeatName.setText(mSeatName);
        tvTotalPrice.setText("Total Price : "+mTotalPrice);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this, ReadNFCActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void fetchData() {
        pbMovieDetail.setVisibility(View.VISIBLE);
        llMovieDetail.setVisibility(View.INVISIBLE);
        Call<MovieItemDao> call = HttpManager.getInstance().getService().getMovieById(mMovieId);
        call.enqueue(new Callback<MovieItemDao>() {
            @Override
            public void onResponse(Call<MovieItemDao> call, Response<MovieItemDao> response) {
                pbMovieDetail.setVisibility(View.INVISIBLE);
                llMovieDetail.setVisibility(View.VISIBLE);
                MovieItemDao movieItemDao = response.body();
                if (movieItemDao == null) {
                    Toast.makeText(PaymentActivity.this, "Sorry, No data to show.", Toast.LENGTH_SHORT).show();
                    return;
                }
                tvPaymentName.setText(movieItemDao.getName());
                tvPaymentDirector.setText(movieItemDao.getDirector());
                tvPaymentType.setText(movieItemDao.getType());
                Glide.with(PaymentActivity.this)
                        .load(movieItemDao.getPoster())
                        .placeholder(R.drawable.gray_image)
                        .into(ivPaymentPoster);
            }

            @Override
            public void onFailure(Call<MovieItemDao> call, Throwable t) {
                pbMovieDetail.setVisibility(View.INVISIBLE);
                llMovieDetail.setVisibility(View.VISIBLE);
                try {
                    Toast.makeText(PaymentActivity.this, t + "", Toast.LENGTH_SHORT).show();
                } catch (NullPointerException e) {}
            }
        });
    }
}
