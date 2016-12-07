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

import java.util.ArrayList;
import java.util.List;

public class SeatSelectActivity extends AppCompatActivity {
    private TextView tvTotalPrice;

    private int mMovieId;
    private String mTheatreName;
    private String mCinemaName;
    private String mTime;
    private int totalPrice = 0;
    private String mSeatName;
    private List<String> seatlist = new ArrayList<String>();

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

        // get intent value
        Intent intent = getIntent();
        mMovieId = intent.getIntExtra("id", 0);
        mTime = intent.getStringExtra("time");
        mTheatreName = intent.getStringExtra("theatre");
        mCinemaName = intent.getStringExtra("cinema");

        // find view
        tvTotalPrice = (TextView)findViewById(R.id.totalprice);
        Button btn = (Button) findViewById(R.id.btnConfirm);

        // set view
        tvTotalPrice.setText("Total Price : "+totalPrice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSeatName = seatlist.size() > 0 ? "" : "Not Select Seat";
                for(int i = 0;i<seatlist.size();i++) {
                    if(i == seatlist.size()-1) {
                        mSeatName += seatlist.get(i);
                    } else {
                        mSeatName += seatlist.get(i)+", ";
                    }
                }
                Intent intent = new Intent(SeatSelectActivity.this, PaymentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id", mMovieId);
                intent.putExtra("time",mTime);
                intent.putExtra("theatre",mTheatreName);
                intent.putExtra("total",totalPrice);
                intent.putExtra("seat",mSeatName);
                intent.putExtra("cinema",mCinemaName);
                startActivity(intent);
                finish();
            }
        });
    }

    public void SelectRedSeat(View view){
        Button seat = (Button)view;
        String rs = seat.getText().toString();

        boolean checkRepeat = false;
        for (String select : seatlist){
            if (select.equals(rs)){
                seat.setBackgroundResource(R.drawable.seatred);
                totalPrice -= 100;
                seatlist.remove(rs);
                checkRepeat = true;
                break;
            }
        }

        if(!checkRepeat){
            seat.setBackgroundResource(R.drawable.seatredcheck);
            totalPrice += 100;
            seatlist.add(rs);
        }

        tvTotalPrice.setText("Total Price : " + totalPrice);
    }
    public void SelectGreenSeat(View view){
        Button seat = (Button)view;
        String rs = seat.getText().toString();

        boolean checkRepeat = false;
        for (String select : seatlist){
            if (select.equals(rs)){
                seat.setBackgroundResource(R.drawable.seatgreen);
                totalPrice -= 150;
                seatlist.remove(rs);
                checkRepeat = true;
                break;
            }
        }

        if(!checkRepeat){
            seat.setBackgroundResource(R.drawable.seatgreencheck);
            totalPrice += 150;
            seatlist.add(rs);
        }

        tvTotalPrice.setText("Total Price : " + totalPrice);
    }

    public void SelectBlueSeat(View view){
        Button seat = (Button)view;
        String rs = seat.getText().toString();

        boolean checkRepeat = false;
        for (String select : seatlist){
            if (select.equals(rs)){
                seat.setBackgroundResource(R.drawable.seatblue);
                totalPrice -= 200;
                seatlist.remove(rs);
                checkRepeat = true;
                break;
            }
        }

        if(!checkRepeat){
            seat.setBackgroundResource(R.drawable.seatbluecheck);
            totalPrice += 200;
            seatlist.add(rs);
        }

        tvTotalPrice.setText("Total Price : " + totalPrice);
    }
}
