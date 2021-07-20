package com.kgstriversmoviejava.movie_app.request;

import com.kgstriversmoviejava.movie_app.Model.MovieModel;
import com.kgstriversmoviejava.movie_app.Response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {


    //https://api.themoviedb.org/3/search/movie?api_key=d27ca1076df344c0b5a2c40dac53f703&query=Jack+Reacher
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
          @Query("api_key") String key,
          @Query("query") String query,
          @Query("page") String page
    );


    //https://api.themoviedb.org/3/movie/{movie_id}?api_key=d27ca1076df344c0b5a2c40dac53f703

    @GET("/3/movie/{movie_id}?")
    Call<MovieModel> getMovie(

        @Path("movie_id") int movie_id,
        @Query("api_key") String api_key
    );
}
