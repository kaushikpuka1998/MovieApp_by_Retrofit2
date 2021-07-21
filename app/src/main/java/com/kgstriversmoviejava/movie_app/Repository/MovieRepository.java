package com.kgstriversmoviejava.movie_app.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kgstriversmoviejava.movie_app.Model.MovieModel;
import com.kgstriversmoviejava.movie_app.request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    public static MovieRepository getInstance()
    {
        if(instance == null)
        {
            instance = new MovieRepository();
        }

        return instance;
    }


    private MovieRepository()
    {
        movieApiClient = MovieApiClient.getInstance();
    }


    public LiveData<List<MovieModel>> getmMovies() {
        return movieApiClient.getMovies();
    }

    public void searchMovieApi(String query,int pageNumber)
    {
        movieApiClient.searchMovieAPI(query,pageNumber);
    }
}
