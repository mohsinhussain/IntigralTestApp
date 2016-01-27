package com.awok.moshin.intigraltestapp.Models;

import com.awok.moshin.intigraltestapp.Utilities.Constants;

/**
 * Created by mohsin on 1/27/2016.
 */
public class Movie {
    private int id;
    private String title;
    private String overView;
    private String backdropPath;
    private String posterPath;
    private int popularity;
    private int voteAverage;
    private String releaseDate;

    public Movie(int id, String title, String overView, String backdropPath, String posterPath, int popularity, int voteAverage, String releaseDate){
        this.id = id;
        this.title = title;
        this.overView = overView;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getBackdropPath() {
        String completeImageURL = Constants.IMAGE_BASE_PATH+backdropPath;
        return completeImageURL;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        String completeImageURL = Constants.IMAGE_BASE_PATH+posterPath;
        return completeImageURL;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
