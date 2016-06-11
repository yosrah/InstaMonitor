package com.yosrahibrahim.instamonitor.main;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;

import com.yosrahibrahim.instamonitor.R;

import instamonitor.yosrahibrahim.com.instamonitorlib.trackers.ActivityTracker;
import instamonitor.yosrahibrahim.com.instamonitorlib.InstaMonitor;
import instamonitor.yosrahibrahim.com.instamonitorlib.models.InstaSession;


/**
 * Created by Yosrah Ibrahim on 6/9/2016.
 *
 *  displays app general info
 */
public class InfoActivity extends ActivityTracker {

    private SwitchCompat ignoreSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.about_instamonitor));
        setSupportActionBar(toolbar);

        /**
         * set up switch control and update value in db accordingly
         */
        ignoreSwitch = (SwitchCompat) findViewById(R.id.ignoreSwitch);
        ignoreSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setIsIgnored(isChecked);
            }
        });

        /**
         * set switch control selection from db
         */
        InstaSession session = InstaMonitor.getSession(this, getClass().getSimpleName());
        if(session != null)
            ignoreSwitch.setChecked(session.isIgnored());
    }
}
