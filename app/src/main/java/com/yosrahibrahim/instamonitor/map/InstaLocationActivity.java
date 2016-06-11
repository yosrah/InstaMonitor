package com.yosrahibrahim.instamonitor.map;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yosrahibrahim.instamonitor.R;

import java.util.HashMap;
import java.util.Map;

import instamonitor.yosrahibrahim.com.instamonitorlib.trackers.ActivityTracker;
import instamonitor.yosrahibrahim.com.instamonitorlib.InstaMonitor;
import instamonitor.yosrahibrahim.com.instamonitorlib.models.InstaSession;

/**
 * Created by Yosrah Ibrahim on 6/10/2016.
 *
 * displays google map
 */
public class InstaLocationActivity extends ActivityTracker implements OnMapReadyCallback {

    private SupportMapFragment supportMapFragment;
    private GoogleMap gMap;

    private SwitchCompat ignoreSwitch;

    private Map<Integer, Marker> markers = new HashMap<Integer, Marker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insta_location);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.wheres_instabug));
        setSupportActionBar(toolbar);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

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

    /**
     * instantiate map
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        gMap = googleMap;

        populateMap();

    }


    /**
     * create markr with instabug location
     */
    private void populateMap() {

        LatLng location = new LatLng(30.036798, 31.013011);
        Marker locationMarker = gMap.addMarker(new MarkerOptions().position(location).title(getString(R.string.you_found_me)));
        locationMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_bug_report));
        markers.put(1, locationMarker);


        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
    }
}
