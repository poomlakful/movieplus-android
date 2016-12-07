package com.example.comsci.movieplus.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.comsci.movieplus.R;

import java.util.ArrayList;
import java.util.List;

public class SeatSelectActivity extends AppCompatActivity {

    int movieId;
    int totalPrice = 0;
    TextView totalprice;
    List<String> seatlist;

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

        seatlist = new ArrayList<String>();
        totalprice = (TextView)findViewById(R.id.totalprice);

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

        totalprice.setText("Total Price : " + totalPrice);
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

        totalprice.setText("Total Price : " + totalPrice);
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

        totalprice.setText("Total Price : " + totalPrice);
    }
}
