package com.example.comsci.movieplus.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by comsci on 9/11/2559.
 */

public class ShowtimeItemDao {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("theatreName")
    @Expose
    private String theatreName;
    @SerializedName("cinemaId")
    @Expose
    private Integer cinemaId;
    @SerializedName("movieID")
    @Expose
    private Integer movieID;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("showTime")
    @Expose
    private List<String> showTime = new ArrayList<String>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getShowTime() {
        return showTime;
    }

    public void setShowTime(List<String> showTime) {
        this.showTime = showTime;
    }
}
