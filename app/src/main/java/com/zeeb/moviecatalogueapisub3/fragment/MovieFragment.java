package com.zeeb.moviecatalogueapisub3.fragment;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeeb.moviecatalogueapisub3.model.movie.ResponseMovie;
import com.zeeb.moviecatalogueapisub3.viewmodel.MainViewModel;
import com.zeeb.moviecatalogueapisub3.R;
import com.zeeb.moviecatalogueapisub3.adapter.AdapterMovie;
import com.zeeb.moviecatalogueapisub3.model.movie.ResultsItemMovie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    @BindView(R.id.rv_movie)
    RecyclerView rvMovie;
    Unbinder unbinder;
    private FragmentManager fragmentManager;
    private AdapterMovie adapterMovie;

    ArrayList<ResultsItemMovie> resultsItemMovieArrayList = new ArrayList<>();
    MainViewModel mainViewModel;

    private ProgressDialog progressDialog;


    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public static MovieFragment newInstance(FragmentManager fragmentManager) {
        MovieFragment fragment = new MovieFragment();
        fragment.setFragmentManager(fragmentManager);
        return fragment;
    }


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.loding));
        progressDialog.show();

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.initMovie();
        mainViewModel.getMoviesModel().observe(this, new Observer<ResponseMovie>() {
            @Override
            public void onChanged(@Nullable ResponseMovie responseMovie) {
                progressDialog.dismiss();
                List<ResultsItemMovie> resultsItemMovies = responseMovie.getResults();
                resultsItemMovieArrayList.addAll(resultsItemMovies);
                adapterMovie.notifyDataSetChanged();
            }
        });

        setupRecyclerView();

    }

    private void setupRecyclerView() {
        if (adapterMovie == null) {
            adapterMovie = new AdapterMovie(getActivity(), resultsItemMovieArrayList);
            rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvMovie.setAdapter(adapterMovie);
            rvMovie.setItemAnimator(new DefaultItemAnimator());
            rvMovie.setNestedScrollingEnabled(true);
        } else {
            adapterMovie.notifyDataSetChanged();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
