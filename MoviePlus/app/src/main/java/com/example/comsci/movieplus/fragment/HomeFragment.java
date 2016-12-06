package com.example.comsci.movieplus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.comsci.movieplus.R;
import com.example.comsci.movieplus.activity.MovieDetailActivity;
import com.example.comsci.movieplus.adapter.HomeAdapter;
import com.example.comsci.movieplus.dao.MovieItemDao;
import com.example.comsci.movieplus.manager.HttpManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by comsci on 9/11/2559.
 */

public class HomeFragment extends Fragment {

    GridView gridview;
    ProgressBar pbHome;

    List<MovieItemDao> mMovieList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initInstances(rootView);
        fetchData();
        return rootView;
    }

    private void fetchData() {
        pbHome.setVisibility(View.VISIBLE);
        Call<List<MovieItemDao>> call = HttpManager.getInstance().getService().getMovieList();
        call.enqueue(new Callback<List<MovieItemDao>>() {
            @Override
            public void onResponse(Call<List<MovieItemDao>> call, Response<List<MovieItemDao>> response) {
                pbHome.setVisibility(View.INVISIBLE);
                mMovieList = response.body();
                if (mMovieList == null) {
                    Toast.makeText(getContext(), "Sory, No data to show.", Toast.LENGTH_SHORT).show();
                }
                gridview.setAdapter(new HomeAdapter(getContext(), mMovieList));
                gridview.setOnItemClickListener(gridViewListener);
            }

            @Override
            public void onFailure(Call<List<MovieItemDao>> call, Throwable t) {
                pbHome.setVisibility(View.INVISIBLE);
                try {
                    Toast.makeText(getContext(), t + "", Toast.LENGTH_SHORT).show();
                } catch (NullPointerException e) {}
            }
        });
    }

    private void initInstances(View rootView) {
        gridview = (GridView) rootView.findViewById(R.id.grid_home);
        pbHome = (ProgressBar) rootView.findViewById(R.id.pbHome);
    }

    private AdapterView.OnItemClickListener gridViewListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v,
                                int position, long id) {
            Intent intent = new Intent(getContext(), MovieDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("id", mMovieList.get(position).getId());
            startActivity(intent);
        }
    };
}
