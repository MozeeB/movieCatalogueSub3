package com.zeeb.moviecatalogueapisub3;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.zeeb.moviecatalogueapisub3.adapter.TablayoutAdapter;
import com.zeeb.moviecatalogueapisub3.fragment.MovieFragment;
import com.zeeb.moviecatalogueapisub3.fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity {

    private TablayoutAdapter tabLayoutAdapter;

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPager();
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public static MainActivity newInstance(FragmentManager fragmentManager) {
        MainActivity fragment = new MainActivity();
        fragment.setFragmentManager(fragmentManager);
        return fragment;
    }

    private void setPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_containerELearning);
        setupViewPager(viewPager);

        TabLayout tabs = (TabLayout) findViewById(R.id.tl_tabsAbsence);
        tabs.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        tabLayoutAdapter = new TablayoutAdapter(getSupportFragmentManager());
        tabLayoutAdapter.addFragment(MovieFragment.newInstance(fragmentManager), getString(R.string.Movies));
        tabLayoutAdapter.addFragment(TvShowFragment.newInstance(fragmentManager), getString(R.string.TvShows));
        viewPager.setAdapter(tabLayoutAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}
