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

import com.zeeb.moviecatalogueapisub3.model.tvShow.ResponseTvShow;
import com.zeeb.moviecatalogueapisub3.viewmodel.MainViewModel;
import com.zeeb.moviecatalogueapisub3.R;
import com.zeeb.moviecatalogueapisub3.adapter.AdapterTvShow;
import com.zeeb.moviecatalogueapisub3.model.tvShow.ResultsItemTvShow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {


    @BindView(R.id.rvTvShow)
    RecyclerView rvTvShow;
    Unbinder unbinder;
    private FragmentManager fragmentManager;

    private AdapterTvShow adapterTvShow;
    ArrayList<ResultsItemTvShow> resultsItemTvShowArrayList = new ArrayList<>();
    MainViewModel mainViewModel;
    private ProgressDialog progressDialog;


    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public static TvShowFragment newInstance(FragmentManager fragmentManager) {
        TvShowFragment fragment = new TvShowFragment();
        fragment.setFragmentManager(fragmentManager);
        return fragment;
    }


    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
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
        mainViewModel.initTvShow();
        mainViewModel.getTvShowsModel().observe(this, new Observer<ResponseTvShow>() {
            @Override
            public void onChanged(@Nullable ResponseTvShow responseTvShow) {
                progressDialog.dismiss();
                List<ResultsItemTvShow> newsArticles = responseTvShow.getResults();
                resultsItemTvShowArrayList.addAll(newsArticles);
                adapterTvShow.notifyDataSetChanged();
            }
        });

        setupRecyclerView();

    }

    private void setupRecyclerView() {
        if (adapterTvShow == null) {
            adapterTvShow = new AdapterTvShow(getActivity(), resultsItemTvShowArrayList);
            rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvTvShow.setAdapter(adapterTvShow);
            rvTvShow.setItemAnimator(new DefaultItemAnimator());
            rvTvShow.setNestedScrollingEnabled(true);
        } else {
            adapterTvShow.notifyDataSetChanged();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
