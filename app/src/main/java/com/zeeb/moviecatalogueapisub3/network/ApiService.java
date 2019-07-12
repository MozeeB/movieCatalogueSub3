package com.zeeb.moviecatalogueapisub3.network;

import com.zeeb.moviecatalogueapisub3.BuildConfig;
import com.zeeb.moviecatalogueapisub3.model.movie.ResponseMovie;
import com.zeeb.moviecatalogueapisub3.model.tvShow.ResponseTvShow;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("discover/movie?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    Call<ResponseMovie> getDataMovie();

    @GET("discover/tv?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    Call<ResponseTvShow> getTvShowData();
}
