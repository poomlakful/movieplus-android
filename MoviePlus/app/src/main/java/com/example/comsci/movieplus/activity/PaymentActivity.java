package com.example.comsci.movieplus.activity;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    Button payment;
    PendingIntent mPendingIntent;
    IntentFilter[] mIntentFilters;
    NfcAdapter nfcAdapter;
    Tag myTag;

    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initInstances();
        fetchData();
        OnclickPayNow();

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
        }

        // create an intent with tag data and deliver to this activity
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        // set an intent filter for all MIME data
        IntentFilter ndefIntent = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        try {
            ndefIntent.addDataType("*/*");
            mIntentFilters = new IntentFilter[] { ndefIntent };
        } catch (Exception e) {
            Log.e("TagDispatch", e.toString());
        }
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
        payment = (Button) findViewById(R.id.btnConfirm);
    }

    private void fetchData() {
        Call<MovieItemDao> call = HttpManager.getInstance().getService().getMovieById(movieId);
        call.enqueue(new Callback<MovieItemDao>() {
            @Override
            public void onResponse(Call<MovieItemDao> call, Response<MovieItemDao> response) {
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

    private void PaymentDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
        builder.setMessage("Confirm payment");
        builder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Toast.makeText(getApplicationContext(), "Payment done!", Toast.LENGTH_SHORT).show();
                myTag = null;
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void OnclickPayNow(){
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myTag != null)
                    PaymentDialog();
                else
                    Toast.makeText(getApplicationContext(), "Tap NFC payment and Select \"PAY NOW\" again ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if(intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)){
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // creating pending intent:
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        // creating intent receiver for NFC events:
        IntentFilter filter = new IntentFilter();
        filter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
        // enabling foreground dispatch for getting intent from NFC event:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{filter}, null);
    }

    @Override
    public void onPause() {
        super.onPause();
        // disabling foreground dispatch:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.disableForegroundDispatch(this);
    }
}
