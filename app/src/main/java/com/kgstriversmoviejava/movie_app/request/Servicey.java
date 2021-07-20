package com.kgstriversmoviejava.movie_app.request;

import com.kgstriversmoviejava.movie_app.Utils.Creden;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicey {


    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(Creden.BASE_URL).addConverterFactory(GsonConverterFactory.create());


    private static Retrofit retrofit = retrofitBuilder.build();

    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    public static  MovieApi getMovieApi()
    {
        return movieApi;
    }
}
