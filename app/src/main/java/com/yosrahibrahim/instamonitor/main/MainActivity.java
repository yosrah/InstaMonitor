package com.yosrahibrahim.instamonitor.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.yosrahibrahim.instamonitor.R;
import com.yosrahibrahim.instamonitor.features.FeaturesActivity;
import com.yosrahibrahim.instamonitor.map.InstaLocationActivity;
import com.yosrahibrahim.instamonitor.reviews.ReviewsActivity;
import com.yosrahibrahim.instamonitor.statistics.AppStatisticsActivity;

import instamonitor.yosrahibrahim.com.instamonitorlib.trackers.ActivityTracker;

/**
 * Created by Yosrah Ibrahim on 6/9/2016.
 *
 *  main landing screen
 *  includes controls to navigate to all features of app
 */

public class MainActivity extends ActivityTracker {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * fab that navigates to statistics
         */
        FloatingActionButton appStatisticsBtn = (FloatingActionButton) findViewById(R.id.appStatisticsBtn);
        appStatisticsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AppStatisticsActivity.class));
            }
        });

        /**
         * go to info screen
         */
        ImageButton infoBtn = (ImageButton) findViewById(R.id.infoBtn);
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoActivity.class));

            }
        });

        /**
         * go to features screen
         */
        TextView features = (TextView) findViewById(R.id.features);
        features.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeaturesActivity.class));
            }
        });

        /**
         * go to reviews screen
         */
        TextView reviews = (TextView) findViewById(R.id.reviews);
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReviewsActivity.class));
            }
        });


        /**
         * go to instabug location screen
         */
        TextView location = (TextView) findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InstaLocationActivity.class));
            }
        });
    }




}
