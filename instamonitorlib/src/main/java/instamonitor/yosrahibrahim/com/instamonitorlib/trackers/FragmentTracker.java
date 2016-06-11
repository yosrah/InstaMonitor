package instamonitor.yosrahibrahim.com.instamonitorlib.trackers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import instamonitor.yosrahibrahim.com.instamonitorlib.database.InstaMonitorDatabaseManager;
import instamonitor.yosrahibrahim.com.instamonitorlib.models.InstaSession;

/**
 * Created by Yosrah Ibrahim on 6/10/2016.
 *
 * tracks time in milliseconds of time spent in fragment
 */
public abstract class FragmentTracker extends Fragment {

    private InstaSession instaSession;

    /**
     * time in milliseconds for session start and end
     */
    private long sessionStartTime, sessionEndTime;


    /**
     * get corresponding session for fragment by its name
     * returns a new session if first time opening session
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instaSession = InstaMonitorDatabaseManager.getInstance().getSession(getContext(), getFragmentName(), InstaSession.TYPE_FRAGMENT, false);

        sessionStartTime = sessionEndTime = 0;

    }


    /**
     * set start time when fragment is visible
     *
     * calculate duration on session end when fragment is invisible
     * update duration for component in db
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser){
            sessionStartTime = System.currentTimeMillis();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if(instaSession != null && sessionStartTime > 0 ){

            sessionEndTime = System.currentTimeMillis();
            long duration = sessionEndTime - sessionStartTime;

            instaSession.setDuration(duration);
            InstaMonitorDatabaseManager.getInstance().updateComponentDuration(getContext(), instaSession);
        }
    }

    /**
     * @return fragment_name
     */
     public abstract String getFragmentName();

}
