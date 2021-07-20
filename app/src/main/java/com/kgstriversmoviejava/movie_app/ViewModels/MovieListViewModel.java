package com.kgstriversmoviejava.movie_app.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kgstriversmoviejava.movie_app.Model.MovieModel;
import com.kgstriversmoviejava.movie_app.Repository.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {



    MovieRepository movieRepository;

    //constructor
    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }


    //getter
    public LiveData<List<MovieModel>> getmovies()
    {


        return movieRepository.getmMovies();
    }


}
