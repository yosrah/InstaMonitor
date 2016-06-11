package com.yosrahibrahim.instamonitor.statistics;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yosrahibrahim.instamonitor.InstaMonitorApp;
import com.yosrahibrahim.instamonitor.R;

import java.util.ArrayList;
import java.util.List;

import instamonitor.yosrahibrahim.com.instamonitorlib.trackers.ActivityTracker;
import instamonitor.yosrahibrahim.com.instamonitorlib.InstaMonitor;
import instamonitor.yosrahibrahim.com.instamonitorlib.models.InstaSession;

public class AppStatisticsActivity extends ActivityTracker {

    private RecyclerView statisticsRecyclerView;
    private LinearLayoutManager layoutManager;
    private StatisticsAdapter statisticsAdapter;
    private Button clearStatsBtn;
    private TextView totalAppTime, emptyView;

    private List<InstaSession> statistics = new ArrayList<InstaSession>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_statistics);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.statistics));
        setSupportActionBar(toolbar);

        statisticsRecyclerView = (RecyclerView) findViewById(R.id.statisticsRecyclerView);
        clearStatsBtn = (Button) findViewById(R.id.clearStatsBtn);
        totalAppTime = (TextView) findViewById(R.id.totalAppTime);
        emptyView = (TextView) findViewById(R.id.emptyView);

        clearStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstaMonitor.resetAppSessions(InstaMonitorApp.getInstance().getApplicationContext());

                statistics.clear();
                statisticsAdapter.notifyDataSetChanged();
                showEmptySessionsView();

                Snackbar.make(clearStatsBtn, getString(R.string.sessions_cleared), Snackbar.LENGTH_SHORT).show();
            }
        });

        layoutManager = new LinearLayoutManager(this);
        statisticsRecyclerView.setHasFixedSize(true);
        statisticsRecyclerView.setLayoutManager(layoutManager);

        totalAppTime.setText(getString(R.string.total_app_time) + " " + InstaMonitor.formatTime(InstaMonitor.getAppDuration(InstaMonitorApp.getInstance().getApplicationContext())));

        statistics = InstaMonitor.getAppSessions(InstaMonitorApp.getInstance().getApplicationContext());

        statisticsAdapter = new StatisticsAdapter(this, statistics);
        statisticsRecyclerView.setAdapter(statisticsAdapter);

        setIsIgnored(true);

        if(statisticsAdapter.getItemCount() == 0)
            showEmptySessionsView();


    }

    private void showEmptySessionsView() {

        emptyView.setVisibility(View.VISIBLE);
        statisticsRecyclerView.setVisibility(View.GONE);
        totalAppTime.setVisibility(View.GONE);
        clearStatsBtn.setVisibility(View.GONE);
    }

}
