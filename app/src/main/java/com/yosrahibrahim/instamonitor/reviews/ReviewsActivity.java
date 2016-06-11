package com.yosrahibrahim.instamonitor.reviews;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.yosrahibrahim.instamonitor.R;
import com.yosrahibrahim.instamonitor.reviews.model.Review;

import java.util.ArrayList;
import java.util.List;

import instamonitor.yosrahibrahim.com.instamonitorlib.trackers.ActivityTracker;

/**
 * Created by Yosrah Ibrahim on 6/10/2016.
 *
 *
 * Display Reviews
 * View data in different display modes
 */
public class ReviewsActivity extends ActivityTracker {

    private Button gridBtn;
    private RecyclerView reviewsRecyclerView;
    private ReviewsAdapter reviewsAdapter;

    private List<Review> reviews = new ArrayList<Review>();

    private boolean isList = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.features));
        setSupportActionBar(toolbar);

        reviewsRecyclerView = (RecyclerView) findViewById(R.id.reviewsRecyclerView);
        gridBtn = (Button) findViewById(R.id.gridBtn);

        reviewsRecyclerView.setHasFixedSize(true);

        restoreDataView();

        getReviews();

        reviewsAdapter = new ReviewsAdapter(this, reviews);
        reviewsRecyclerView.setAdapter(reviewsAdapter);

        /**
         * switch between list and grid views
         */
        gridBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (gridBtn.getText().toString().equalsIgnoreCase(getString(R.string.grid))) {
                    reviewsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    gridBtn.setText(getString(R.string.list));
                } else {
                    reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(ReviewsActivity.this));
                    gridBtn.setText(getString(R.string.grid));
                }
            }
        });

    }

    /**
     * save any user selected data for configuration changes
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putBoolean("list_type_list", reviewsRecyclerView.getLayoutManager() instanceof LinearLayoutManager);
        super.onSaveInstanceState(outState);
    }

    /**
     * restore any user selected data for configuration changes
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isList = savedInstanceState.getBoolean("list_type_list", false);

        restoreDataView();

    }

    /**
     * restore user selection
     */
    private void restoreDataView() {
        if (isList) {
            reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            gridBtn.setText(getString(R.string.grid));
        } else {
            reviewsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            gridBtn.setText(getString(R.string.list));
        }
    }

    /**
     * get generated Reviews
     */
    private void getReviews() {

        Review lyft = new Review();
        lyft.setReview(getString(R.string.lyft_review));
        lyft.setReviewer(getString(R.string.lyft_reviewer));
        reviews.add(lyft);

        Review nextdoor = new Review();
        nextdoor.setReview(getString(R.string.nextdoor_review));
        nextdoor.setReviewer(getString(R.string.nextdoor_reviewer));
        reviews.add(nextdoor);

        Review tilt = new Review();
        tilt.setReview(getString(R.string.tilt_review));
        tilt.setReviewer(getString(R.string.tilt_reviewer));
        reviews.add(tilt);

        Review yahoo = new Review();
        yahoo.setReview(getString(R.string.yahoo_review));
        yahoo.setReviewer(getString(R.string.yahoo_reviewer));
        reviews.add(yahoo);

    }
}
