package com.example.comsci.movieplus.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.comsci.movieplus.R;
import com.example.comsci.movieplus.fragment.HomeFragment;
import com.example.comsci.movieplus.fragment.MenuFragment;
import com.example.comsci.movieplus.fragment.ShowtimeFragment;

public class MainActivity extends AppCompatActivity {

    Button btnHome;
    Button btnShowtime;
    Button btnMenu;
    ImageView ivHome;
    ImageView ivShowtime;
    ImageView ivMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstances();
        initFragment(savedInstanceState);
    }

    private void initInstances() {
        //Set action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        // Find view
        btnHome = (Button) findViewById(R.id.btnHome);
        btnShowtime = (Button) findViewById(R.id.btnShowtime);
        btnMenu = (Button) findViewById(R.id.btnMenu);
        ivHome = (ImageView) findViewById(R.id.ivHome);
        ivShowtime = (ImageView) findViewById(R.id.ivShowtime);
        ivMenu = (ImageView) findViewById(R.id.ivMenu);

        // Set listener
        btnHome.setOnClickListener(btnHomeListener);
        btnShowtime.setOnClickListener(btnShowtimeListener);
        btnMenu.setOnClickListener(btnMenuListener);
    }

    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            setImageButtonDefault();
            ivHome.setImageResource(R.drawable.home_click);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.MainContainer, new HomeFragment())
                    .commit();
        }
    }

    private void setImageButtonDefault() {
        ivHome.setImageResource(R.drawable.home);
        ivShowtime.setImageResource(R.drawable.showtime);
        ivMenu.setImageResource(R.drawable.menu);
    }

    private View.OnClickListener btnHomeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setImageButtonDefault();
            ivHome.setImageResource(R.drawable.home_click);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.MainContainer, new HomeFragment())
                    .commit();
        }
    };

    private View.OnClickListener btnShowtimeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setImageButtonDefault();
            ivShowtime.setImageResource(R.drawable.showtime_click);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.MainContainer, new ShowtimeFragment())
                    .commit();
        }
    };

    private View.OnClickListener btnMenuListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setImageButtonDefault();
            ivMenu.setImageResource(R.drawable.menu_click);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.MainContainer, new MenuFragment())
                    .commit();
        }
    };
}
