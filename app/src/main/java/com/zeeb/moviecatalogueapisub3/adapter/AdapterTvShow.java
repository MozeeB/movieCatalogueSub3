package com.zeeb.moviecatalogueapisub3.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zeeb.moviecatalogueapisub3.BuildConfig;
import com.zeeb.moviecatalogueapisub3.DetailTvShowActivity;
import com.zeeb.moviecatalogueapisub3.R;
import com.zeeb.moviecatalogueapisub3.model.tvShow.ResultsItemTvShow;

import java.util.List;

public class AdapterTvShow extends RecyclerView.Adapter<AdapterTvShow.ViewHolder> {

    private Context context;
    private List<ResultsItemTvShow> resultsItemTvShowList;


    public AdapterTvShow(Context context, List<ResultsItemTvShow> resultsItemMovies) {
        this.context = context;
        this.resultsItemTvShowList = resultsItemMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {


        viewHolder.judul.setText(resultsItemTvShowList.get(i).getName());
        viewHolder.score.setText(String.valueOf(resultsItemTvShowList.get(i).getVoteAverage()));
        Glide.with(context).load(BuildConfig.URLIMAGE + resultsItemTvShowList.get(i).getPosterPath()).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTvShowActivity.class);
                intent.putExtra(DetailTvShowActivity.EXTRATVSHOW, resultsItemTvShowList.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsItemTvShowList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView judul, score;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.tvJudulMoview);
            score = itemView.findViewById(R.id.tvScoreMovie);
            img = itemView.findViewById(R.id.imgMoview);
        }
    }
}
