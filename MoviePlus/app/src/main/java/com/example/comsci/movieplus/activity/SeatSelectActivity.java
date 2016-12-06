package com.example.comsci.movieplus.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comsci.movieplus.R;

public class SeatSelectActivity extends AppCompatActivity {

    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_select);
        initInstances();
    }

    private void initInstances() {
        //Set action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("Seat Select");

        Intent intent = getIntent();
        movieId = intent.getIntExtra("id", 0);

        Button btn = (Button) findViewById(R.id.btnConfirm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeatSelectActivity.this, PaymentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id", movieId);
                startActivity(intent);
                //finish();
            }
        });
    }

    public void SelectSeat(View view){
        TextView txt1 = (TextView)findViewById(R.id.txt1);
        Button seat = (Button)view;
        String SeatNo = seat.getText().toString();
        txt1.setText("Seat No. : " + SeatNo);
        seat.setBackgroundResource(R.drawable.sofacheckred);
    }
}
