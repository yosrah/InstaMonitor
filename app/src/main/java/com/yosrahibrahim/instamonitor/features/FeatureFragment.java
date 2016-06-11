package com.yosrahibrahim.instamonitor.features;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yosrahibrahim.instamonitor.R;

import instamonitor.yosrahibrahim.com.instamonitorlib.trackers.FragmentTracker;

/**
 * Created by Yosrah Ibrahim on 6/10/2016.
 */
public class FeatureFragment extends FragmentTracker {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_TITLE = "title";
    private static final String ARG_DESCRIPTION = "desc";

    public FeatureFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FeatureFragment newInstance(String title, String desc) {
        FeatureFragment fragment = new FeatureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESCRIPTION, desc);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_features, container, false);

        TextView title = (TextView) rootView.findViewById(R.id.title);
        TextView desc = (TextView) rootView.findViewById(R.id.desc);

        title.setText(getArguments().getString(ARG_TITLE));
        desc.setText(getArguments().getString(ARG_DESCRIPTION));

        return rootView;
    }

    /**
     *
     * @return fragment name
     */
    @Override
    public String getFragmentName() {
        return getArguments().getString(ARG_TITLE) + " " + getString(R.string.feature_fragment);
    }
}
