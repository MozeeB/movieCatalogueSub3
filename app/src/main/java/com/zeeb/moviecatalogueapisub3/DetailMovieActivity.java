package com.zeeb.moviecatalogueapisub3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zeeb.moviecatalogueapisub3.model.movie.ResultsItemMovie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRAMOVIE = "extra_movie";
    @BindView(R.id.imgDetailMovie)
    ImageView imgDetailMovie;
    @BindView(R.id.tvJudulDetail)
    TextView tvJudulDetail;
    @BindView(R.id.tvDetailScore)
    TextView tvDetailScore;
    @BindView(R.id.tvDetailDes)
    TextView tvDetailDes;

    private ProgressDialog progressDialog;

    private static final String STATE_RESULT = "state_result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loding));
        progressDialog.show();

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            Glide.with(this).load(result).into(imgDetailMovie);
            tvJudulDetail.setText(result);
            tvDetailScore.setText(result);
            tvDetailDes.setText(result);
            progressDialog.dismiss();
        }

        setUpDelay();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ResultsItemMovie resultsItemMovie = getIntent().getParcelableExtra(EXTRAMOVIE);
        outState.putString(STATE_RESULT, resultsItemMovie.getBackdropPath());
        outState.putString(STATE_RESULT, resultsItemMovie.getOriginalTitle());
        outState.putString(STATE_RESULT, String.valueOf(resultsItemMovie.getVoteAverage()));
        outState.putString(STATE_RESULT, resultsItemMovie.getOverview());

    }

    private void setUpDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                ResultsItemMovie resultsItemMovie = getIntent().getParcelableExtra(EXTRAMOVIE);
                getSupportActionBar().setTitle(getString(R.string.detailfilm) + " " + resultsItemMovie.getOriginalTitle());
                Glide.with(DetailMovieActivity.this).load(BuildConfig.URLIMAGE + resultsItemMovie.getBackdropPath()).into(imgDetailMovie);
                tvJudulDetail.setText(resultsItemMovie.getOriginalTitle());
                tvDetailScore.setText(String.valueOf(resultsItemMovie.getVoteAverage()));
                tvDetailDes.setText(resultsItemMovie.getOverview());
            }
        }, 2000);
    }
}
