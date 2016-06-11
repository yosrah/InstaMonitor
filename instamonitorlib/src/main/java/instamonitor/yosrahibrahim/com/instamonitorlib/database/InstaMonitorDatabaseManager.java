package instamonitor.yosrahibrahim.com.instamonitorlib.database;

import android.content.Context;

import java.util.List;

import instamonitor.yosrahibrahim.com.instamonitorlib.database.tables.InstaSessionTable;
import instamonitor.yosrahibrahim.com.instamonitorlib.models.InstaSession;

/**
 * Created by Yosrah Ibrahim on 6/9/2016.
 */
public class InstaMonitorDatabaseManager {

    private static InstaMonitorDatabaseManager instance;

    /**
     * @return An instance of database manager.
     */
    public synchronized static InstaMonitorDatabaseManager getInstance() {
        if (instance == null)
            instance = new InstaMonitorDatabaseManager();
        return instance;
    }

    public void initializeTables(Context context) {
        InstaSessionTable.getInstance(context);

    }

    /**
     * update isIgnored column for corresponding session
     * @param context
     * @param session
     */
    public void updateComponent(Context context, InstaSession session){

        InstaSessionTable.getInstance(context).updateIsIgnored(session);
    }

    /**
     * update duration column for corresponding session
     * @param context
     * @param session
     */
    public void updateComponentDuration(Context context, InstaSession session){
        InstaSessionTable.getInstance(context).updateDuration(session);
    }

    /**
     * get session for corresponding component
     * if session doesn't exist, create new entry for component in the db
     *
     * @param context
     * @param componentName
     * @param componentType
     * @param isIgnored
     *
     * @return InstaSession
     */
    public InstaSession getSession(Context context, String componentName, int componentType, boolean isIgnored){

        InstaSession session = null;

        session = InstaSessionTable.getInstance(context).getSession(componentName);

        if(session == null
                &&  InstaSessionTable.getInstance(context).insert(new InstaSession(componentName, componentType, isIgnored, 0)) )
            session = InstaSessionTable.getInstance(context).getSession(componentName);


        return session;

    }


    /**
     * get session for corresponding component
     *
     * @param context
     * @param componentName
     *
     * @return InstaSession
     */
    public InstaSession getSession(Context context, String componentName){

        return InstaSessionTable.getInstance(context).getSession(componentName);

    }

    /**
     * get total time spent in app excluding ignored components
     *
     * @param context
     *
     * @return duration
     */
    public long getApplicationDuration(Context context){
        return InstaSessionTable.getInstance(context).getSumOfDuration();
    }

    /**
     * retrieve all session entries excluding ignored components
     *
     * @param context
     *
     * @return sessions
     */
    public List<InstaSession> getAllSessions(Context context){
        return InstaSessionTable.getInstance(context).getAll(InstaSessionTable.COLUMN_IS_IGNORED + " = ? AND " + InstaSessionTable.COLUMN_DURATION + " > 0", new String[] { "0" });
    }



}
