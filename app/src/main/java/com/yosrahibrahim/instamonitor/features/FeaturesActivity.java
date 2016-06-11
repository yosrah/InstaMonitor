package com.yosrahibrahim.instamonitor.features;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yosrahibrahim.instamonitor.R;

public class FeaturesActivity extends AppCompatActivity {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private String[] featureTitles;
    private String[] featureDescriptions;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);

         featureTitles = new String[]{getString(R.string.feature_title_in_app_support), getString(R.string.feature_title_in_app_bug), getString(R.string.feature_title_live_convo)};
         featureDescriptions = new String[]{getString(R.string.feature_desc_in_app_support), getString(R.string.feature_desc_in_app_bug), getString(R.string.feature_desc_live_convo)};


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.features));
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);


        // Create the adapter that will return a fragment for each of the three
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // attach the ViewPager to the tab layout
        tabLayout.setupWithViewPager(mViewPager);



    }

    /**
     * A {@link SectionsPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a FeatureFragment
            return FeatureFragment.newInstance(featureTitles[position], featureDescriptions[position]);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.in_app_support);
                case 1:
                    return getString(R.string.in_app_bug_reporter);
                case 2:
                    return getString(R.string.live_convo);
            }
            return null;
        }
    }
}
