package com.kgstriversmoviejava.movie_app.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kgstriversmoviejava.movie_app.Model.MovieModel;

public class MovieResponse {


    @SerializedName("results")
    @Expose

    private MovieModel movie;

    public MovieModel getmovie(){
        return movie;
    }


    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
