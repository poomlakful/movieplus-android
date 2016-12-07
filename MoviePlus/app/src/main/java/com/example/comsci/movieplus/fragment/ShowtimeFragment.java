package com.example.comsci.movieplus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comsci.movieplus.R;
import com.example.comsci.movieplus.activity.MovieDetailActivity;
import com.example.comsci.movieplus.adapter.ShowtimeAdapter;
import com.example.comsci.movieplus.dao.ShowtimeItemDao;
import com.example.comsci.movieplus.manager.HttpManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by comsci on 9/11/2559.
 */

public class ShowtimeFragment extends Fragment {
    private Spinner snCinema;
    private ListView lvShowtime;
    private ProgressBar pbShowtime;

    private List<ShowtimeItemDao> mShowtimeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_showtime, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        snCinema = (Spinner) rootView.findViewById(R.id.snCinema);
        lvShowtime = (ListView) rootView.findViewById(R.id.lvShowtime);
        pbShowtime = (ProgressBar) rootView.findViewById(R.id.pbShowtime);

        snCinema.setOnItemSelectedListener(snCinemaListener);
    }

    private AdapterView.OnItemSelectedListener snCinemaListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            pbShowtime.setVisibility(View.VISIBLE);
            lvShowtime.setVisibility(View.INVISIBLE);
            Call<List<ShowtimeItemDao>> call = HttpManager.getInstance().getService().getMovieByCinemaId(i + 1);
            call.enqueue(showtimeCallback);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private AdapterView.OnItemClickListener lvShowtimeListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getContext(), MovieDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("id", mShowtimeList.get(i).getMovieID());
            startActivity(intent);
        }
    };

    private Callback<List<ShowtimeItemDao>> showtimeCallback = new Callback<List<ShowtimeItemDao>>() {
        @Override
        public void onResponse(Call<List<ShowtimeItemDao>> call, Response<List<ShowtimeItemDao>> response) {
            pbShowtime.setVisibility(View.INVISIBLE);
            lvShowtime.setVisibility(View.VISIBLE);
            mShowtimeList = response.body();
            if (mShowtimeList == null || mShowtimeList.size() == 0) {
                Toast.makeText(getContext(), "Sory, No data to show.", Toast.LENGTH_SHORT).show();
            } else {
                lvShowtime.setAdapter(new ShowtimeAdapter(getContext(), mShowtimeList));
                lvShowtime.setOnItemClickListener(lvShowtimeListener);
            }
        }

        @Override
        public void onFailure(Call<List<ShowtimeItemDao>> call, Throwable t) {
            pbShowtime.setVisibility(View.INVISIBLE);
            lvShowtime.setVisibility(View.VISIBLE);
            try {
                Toast.makeText(getContext(), t + "", Toast.LENGTH_SHORT).show();
            } catch (NullPointerException e) {}
        }
    };
}
