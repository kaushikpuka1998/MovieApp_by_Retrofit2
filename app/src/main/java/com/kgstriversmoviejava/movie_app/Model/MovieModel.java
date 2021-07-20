package com.kgstriversmoviejava.movie_app.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable { //here parcelable used due to pass the data to another activity

    private String title;
    private String poster_path;
    private String release_date;
    private float popularity;
    private float vote_average;
    private  String overview;



    //Constructor
    public MovieModel(String title, String poster_path, String release_date, int popularity, float vote_average, String overview) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.popularity = popularity;
        this.vote_average = vote_average;
        this.overview = overview;
    }


    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        popularity = in.readInt();
        vote_average = in.readFloat();
        overview = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };



    //getter
    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public float getPopularity() {
        return popularity;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeFloat(popularity);
        dest.writeFloat(vote_average);
        dest.writeString(overview);
    }
}
