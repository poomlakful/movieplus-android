package com.example.comsci.movieplus.manager;


import android.content.Context;
import retrofit2.Retrofit;

/**
 * Created by comsci on 9/11/2559.
 */

public class HttpManager {

    private static HttpManager instance;

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    private Context mContext;
    private ApiService service;

    private HttpManager() {
        mContext = Contextor.getInstance().getContext();

        //Building Api Service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("localhost:5555/")
                .build();

        service = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return service;
    }

}
