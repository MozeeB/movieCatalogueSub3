package com.zeeb.moviecatalogueapisub3;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zeeb.moviecatalogueapisub3.model.tvShow.ResultsItemTvShow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String EXTRATVSHOW = "extra_tvshow";
    @BindView(R.id.imgDetailTvShow)
    ImageView imgDetailTvShow;
    @BindView(R.id.tvJudulDetailTvShow)
    TextView tvJudulDetailTvShow;
    @BindView(R.id.tvDetailScoreTvShow)
    TextView tvDetailScoreTvShow;
    @BindView(R.id.tvDetailDesTvShow)
    TextView tvDetailDesTvShow;

    private ProgressDialog progressDialog;

    private static final String STATE_RESULT = "state_result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        ButterKnife.bind(this);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loding));
        progressDialog.show();

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            Glide.with(this).load(result).into(imgDetailTvShow);
            tvJudulDetailTvShow.setText(result);
            tvDetailScoreTvShow.setText(result);
            tvDetailDesTvShow.setText(result);
            progressDialog.dismiss();
        }
        setUpDelay();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ResultsItemTvShow resultsItemTvShow = getIntent().getParcelableExtra(EXTRATVSHOW);
        outState.putString(STATE_RESULT, resultsItemTvShow.getBackdropPath());
        outState.putString(STATE_RESULT, resultsItemTvShow.getName());
        outState.putString(STATE_RESULT, String.valueOf(resultsItemTvShow.getVoteAverage()));
        outState.putString(STATE_RESULT, resultsItemTvShow.getOverview());

    }

    private void setUpDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                ResultsItemTvShow resultsItemTvShow = getIntent().getParcelableExtra(EXTRATVSHOW);
                getSupportActionBar().setTitle(getString(R.string.detailfilm) + " " + resultsItemTvShow.getName());
                Glide.with(DetailTvShowActivity.this).load(BuildConfig.URLIMAGE + resultsItemTvShow.getBackdropPath()).into(imgDetailTvShow);
                tvJudulDetailTvShow.setText(resultsItemTvShow.getName());
                tvDetailScoreTvShow.setText(String.valueOf(resultsItemTvShow.getVoteAverage()));
                tvDetailDesTvShow.setText(resultsItemTvShow.getOverview());
            }
        }, 2000);
    }
}
