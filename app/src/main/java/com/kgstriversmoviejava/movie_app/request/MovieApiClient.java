package com.kgstriversmoviejava.movie_app.request;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kgstriversmoviejava.movie_app.AppExecutors;
import com.kgstriversmoviejava.movie_app.Model.MovieModel;
import com.kgstriversmoviejava.movie_app.Response.MovieSearchResponse;
import com.kgstriversmoviejava.movie_app.Utils.Creden;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    private MutableLiveData<List<MovieModel>> mMovies;

    private static MovieApiClient instance;

    private RetrivemoviesRunnable retrivemoviesRunnable;

    public static MovieApiClient getInstance(){


        if(instance == null)
        {
            instance = new MovieApiClient();
        }


        return instance;
    }

    public MovieApiClient() {
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>>getMovies()
    {
        return mMovies;
    }



    //This Method actually using by calling through the classes
    public void searchMovieAPI(String query,int pageNumber)
    {

        if(retrivemoviesRunnable!= null)
        {
            retrivemoviesRunnable = null;
        }

        retrivemoviesRunnable = new RetrivemoviesRunnable(query,pageNumber);
        final Future myHandler = AppExecutors.getInstance().netWorkIO().submit(retrivemoviesRunnable);


        //Not to crash (ANR reducing)the retrofit call that's why using another executor for cancelling that
        AppExecutors.getInstance().netWorkIO().schedule(new Runnable() {
            @Override
            public void run() {
              //Cancelling the Retrofit Call
                myHandler.cancel(true);
            }
        },2000, TimeUnit.MILLISECONDS);


    }


    //Retriving data from RESTAPI by Runnable Class
    //@ type Query ID and name
    ////https://api.themoviedb.org/3/search/movie?api_key=d27ca1076df344c0b5a2c40dac53f703&query=Jack+Reacher
    private class RetrivemoviesRunnable implements Runnable{


        private  String query;
        private int pageNumber;
        boolean cancelrequest;


        public RetrivemoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelrequest = false;
        }

        @Override
        public void run() {

            try{
                Response response = getmovies(query,pageNumber).execute();

                if(cancelrequest)
                {
                    return;
                }
                if(response.code()==200)
                {
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());

                    if(pageNumber == 1)
                    {

                        //Sending Data to LiveData
                        //PostValue:used for Background Thread
                        //Setvalue :not for background thread
                        mMovies.postValue(list);
                    }
                    else
                    {
                        List<MovieModel> currentmovies  = mMovies.getValue();
                        currentmovies.addAll(list);
                        mMovies.postValue(currentmovies);
                    }
                }
                else
                {
                    String error = response.errorBody().string();
                    Log.v("Tag:","Error:"+error);

                    mMovies.postValue(null);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
                mMovies.postValue(null);
            }
            if(cancelrequest)
            {
                return;
            }



        }

        private Call<MovieSearchResponse> getmovies(String query,int pageNumber)
        {
            return Servicey.getMovieApi().searchMovie(Creden.API_KEY,query,pageNumber);
        }

        private void cancelRequest()
        {
            Log.v("Tag:","Cancelling Request");
            cancelrequest = true;
        }
    }


}
