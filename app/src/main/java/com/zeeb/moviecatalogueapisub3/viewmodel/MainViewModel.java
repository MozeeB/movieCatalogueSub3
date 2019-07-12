package com.zeeb.moviecatalogueapisub3.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.zeeb.moviecatalogueapisub3.model.movie.MovieRepo;
import com.zeeb.moviecatalogueapisub3.model.movie.ResponseMovie;
import com.zeeb.moviecatalogueapisub3.model.tvShow.ResponseTvShow;
import com.zeeb.moviecatalogueapisub3.model.tvShow.TvShowRepo;

public class MainViewModel extends ViewModel {

    private MutableLiveData<ResponseMovie> responseMovieMutableLiveData;
    private MovieRepo movieRepo;

    public void initMovie() {
        if (responseMovieMutableLiveData != null) {
            return;
        }
        movieRepo = MovieRepo.getInstance();
        responseMovieMutableLiveData = movieRepo.getMovies();
    }

    public LiveData<ResponseMovie> getMoviesModel() {
        return responseMovieMutableLiveData;
    }

    //TVSHOW
    private MutableLiveData<ResponseTvShow> responseTvShowMutableLiveData;
    private TvShowRepo tvShowRepo;

    public void initTvShow() {
        if (responseTvShowMutableLiveData != null) {
            return;
        }
        tvShowRepo = TvShowRepo.getInstance();
        responseTvShowMutableLiveData = tvShowRepo.getTVShow();
    }

    public LiveData<ResponseTvShow> getTvShowsModel() {
        return responseTvShowMutableLiveData;
    }
}
