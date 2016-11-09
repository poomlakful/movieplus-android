package com.example.comsci.movieplus.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by comsci on 9/11/2559.
 */

public class MovieItemDao {
    @SerializedName("id")
    @Expose
    private String id;


    @SerializedName("time")
    @Expose
    private String time;


    @SerializedName("trailer")
    @Expose
    private String trailer;


    @SerializedName("detail")
    @Expose
    private String detail;


    @SerializedName("status")
    @Expose
    private String status;


    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("poster")
    @Expose
    private String poster;


    @SerializedName("director")
    @Expose
    private String director;


    @SerializedName("type")
    @Expose
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
