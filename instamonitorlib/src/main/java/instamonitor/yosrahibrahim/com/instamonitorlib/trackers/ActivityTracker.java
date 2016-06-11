package instamonitor.yosrahibrahim.com.instamonitorlib.trackers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import instamonitor.yosrahibrahim.com.instamonitorlib.database.InstaMonitorDatabaseManager;
import instamonitor.yosrahibrahim.com.instamonitorlib.models.InstaSession;

/**
 * Created by Yosrah Ibrahim on 6/10/2016.
 *
 * Tracks time spent in activity in milliseconds
 */
public class ActivityTracker extends AppCompatActivity {

    private InstaSession instaSession;

    /**
     * time in milliseconds for session start and end
     */
    private long sessionStartTime, sessionEndTime;


    /**
     * get corresponding session for activity by its simple name
     * returns a new session if first time opening session
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instaSession = InstaMonitorDatabaseManager.getInstance().getSession(this, getClass().getSimpleName(), InstaSession.TYPE_ACTIVITY, false);

    }


    /**
     * set start time when activity is visible
     */
    @Override
    protected void onResume() {
        super.onResume();

        sessionStartTime = System.currentTimeMillis();

    }


    /**
     * caculate duration on session end
     * update duration for component in db
     */
    @Override
    protected void onPause() {
        super.onPause();

        sessionEndTime = System.currentTimeMillis();
        long duration = sessionEndTime - sessionStartTime;
        instaSession.setDuration(duration);

        if(instaSession != null)
            InstaMonitorDatabaseManager.getInstance().updateComponentDuration(this, instaSession);

    }

    /**
     * set whether this activity will be ignored on overall app duration calculation
     *
     * @param isIgnored
     */
    public void setIsIgnored(boolean isIgnored){

        if(instaSession != null){
            instaSession.setIgnored(isIgnored);
            InstaMonitorDatabaseManager.getInstance().updateComponent(this, instaSession);
        }

    }
}
