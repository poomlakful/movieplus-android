package com.example.comsci.movieplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.comsci.movieplus.R;
import com.example.comsci.movieplus.adapter.HomeAdapter;

/**
 * Created by comsci on 9/11/2559.
 */

public class HomeFragment extends Fragment {

    GridView gridview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        gridview = (GridView) rootView.findViewById(R.id.grid_home);
        gridview.setAdapter(new HomeAdapter(getContext()));
        gridview.setOnItemClickListener(gridViewListener);
    }

    private AdapterView.OnItemClickListener gridViewListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v,
                                int position, long id) {
            Toast.makeText(getContext(), "" + position,
                    Toast.LENGTH_SHORT).show();
        }
    };
}
