package com.example.comsci.movieplus.manager;

import com.example.comsci.movieplus.dao.MovieItemDao;
import com.example.comsci.movieplus.dao.ShowtimeItemDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by comsci on 9/11/2559.
 */

public interface ApiService {
    @GET("movie")
    Call<List<MovieItemDao>> getMovieList();

    @GET("movie/{id}")
    Call<MovieItemDao> getMovieById(@Path("id") int id);

    @GET("showtime/{cinemaId}")
    Call<List<ShowtimeItemDao>> getMovieByCinemaId(@Path("cinemaId") int cinemaId);
}
