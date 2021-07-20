package com.kgstriversmoviejava.movie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kgstriversmoviejava.movie_app.Model.MovieModel;
import com.kgstriversmoviejava.movie_app.Response.MovieSearchResponse;
import com.kgstriversmoviejava.movie_app.Utils.Creden;
import com.kgstriversmoviejava.movie_app.request.MovieApi;
import com.kgstriversmoviejava.movie_app.request.Servicey;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {


    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btncheck);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GetRetrofitResponse();
                GetRetrofitResponsebymovieId();
            }


        });



    }

    private void GetRetrofitResponse() {

        MovieApi movieApi = Servicey.getMovieApi();

        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(Creden.API_KEY,"Action","1");


        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {



                if(response.code() == 200)
                {
                    Log.v("Tag==========>","The Response:"+response.body().toString());

                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());

                    for(MovieModel movie:movies)
                    {
                        Log.v("Tag:","The List:Name: "+movie.getTitle()+','+movie.getPopularity());
                    }
                }
                else
                {
                    try{
                        Log.v("Tag:","Error"+response.errorBody().toString());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


    private void GetRetrofitResponsebymovieId()
    {
        MovieApi movieApi = Servicey.getMovieApi();

        Call<MovieModel> responseCall = movieApi.getMovie(
                565,
               Creden.API_KEY
        );


        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

                if(response.code() == 200)
                {
                    MovieModel movies = response.body();

                    Log.v("TAG FOR ID:====>","The Response id: " +movies.getTitle());
                }
                else
                {
                    try {
                        Log.v("TAG FOR ID:====>","Error: " +response.errorBody());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {


            }
        });
    }
}