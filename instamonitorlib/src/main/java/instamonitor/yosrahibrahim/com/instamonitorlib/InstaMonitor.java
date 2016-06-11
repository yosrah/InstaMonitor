package instamonitor.yosrahibrahim.com.instamonitorlib;

import android.content.Context;

import java.util.List;
import java.util.concurrent.TimeUnit;

import instamonitor.yosrahibrahim.com.instamonitorlib.database.InstaMonitorDatabaseManager;
import instamonitor.yosrahibrahim.com.instamonitorlib.database.tables.InstaSessionTable;
import instamonitor.yosrahibrahim.com.instamonitorlib.models.InstaSession;

/**
 * Created by Yosrah Ibrahim on 6/9/2016.
 */
public class InstaMonitor {


    /**
     * gets total app duration excluding sessions that are set to be ignored
     *
     * @param context
     *
     * @return duration
     */
    public static long getAppDuration(Context context){
        return InstaMonitorDatabaseManager.getInstance().getApplicationDuration(context);
    }

    /**
     * gets all sapp sessions excluding sessions that are set to be ignored
     *
     * @param context
     *
     * @return sessions
     */
    public static List<InstaSession> getAppSessions(Context context){
        return InstaMonitorDatabaseManager.getInstance().getAllSessions(context);
    }

    /**
     * gets session for component
     *
     * @param context
     * @param componentName
     */
    public static InstaSession getSession(Context context, String componentName){
        return InstaMonitorDatabaseManager.getInstance().getSession(context, componentName);
    }

    /**
     * clears all app session from db
     *
     * @param context
     */
    public static void resetAppSessions(Context context){
        InstaSessionTable.getInstance(context).deleteAll();
    }


    /**
     * formats time in milliseconds to user friendly text
     *
     * @return formatted time
     */
    public static String formatTime(long timeInMilli){

        StringBuilder formattedTime = new StringBuilder();

        long hours = TimeUnit.MILLISECONDS.toHours(timeInMilli);
        long mins =  TimeUnit.MILLISECONDS.toMinutes(timeInMilli) % TimeUnit.HOURS.toMinutes(1);
        long secs = TimeUnit.MILLISECONDS.toSeconds(timeInMilli) % TimeUnit.MINUTES.toSeconds(1);

        if(hours > 0)
            formattedTime.append(hours + " hrs ");

        if(mins > 0)
            formattedTime.append(mins + " mins ");

        if(secs > 0)
            formattedTime.append(secs + " secs ");

        return formattedTime.toString();
    }
}
