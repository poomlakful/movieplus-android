package com.example.comsci.movieplus.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.comsci.movieplus.R;

public class SeatSelectActivity extends AppCompatActivity {

    int movieId;
    int Rseat[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
    int Gseat[] = { 0,0,0,0,0,0,0,0 };
    int Bseat[] = { 0,0,0,0, };

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

    public void SelectRedSeat(View view){
        Button seat = (Button)view;
        String rs = seat.getText().toString();
        int i = 0, j = 0;
        if(rs.charAt(0) == 'F') { i = 0; }
        else if(rs.charAt(0) == 'E') { i = 1; }
        else if(rs.charAt(0) == 'D') { i = 2; }
        else { i = 3; }
        j = Integer.parseInt(rs.substring(1)) - 1;
        if (Rseat[i][j] == 0){
            seat.setBackgroundResource(R.drawable.seatredcheck);
            Rseat[i][j] = 1;
        }
        else {
            seat.setBackgroundResource(R.drawable.seatred);
            Rseat[i][j] = 0;
        }
    }
    public void SelectGreenSeat(View view){
        Button seat = (Button)view;
        String rs = seat.getText().toString();
        int i = 0, j = 0;
        j = Integer.parseInt(rs.substring(1)) - 1;
        if (Rseat[i][j] == 0){
            seat.setBackgroundResource(R.drawable.seatgreencheck);
            Rseat[i][j] = 1;
        }
        else {
            seat.setBackgroundResource(R.drawable.seatgreen);
            Rseat[i][j] = 0;
        }
    }

    public void SelectBlueSeat(View view){
        Button seat = (Button)view;
        String rs = seat.getText().toString();
        int i = 0, j = 0;
        j = Integer.parseInt(rs.substring(1)) - 1;
        if (Rseat[i][j] == 0){
            seat.setBackgroundResource(R.drawable.seatbluecheck);
            Rseat[i][j] = 1;
        }
        else {
            seat.setBackgroundResource(R.drawable.seatblue);
            Rseat[i][j] = 0;
        }
    }
}
