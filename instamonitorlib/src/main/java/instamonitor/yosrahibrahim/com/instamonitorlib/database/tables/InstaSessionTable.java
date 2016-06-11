package instamonitor.yosrahibrahim.com.instamonitorlib.database.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import instamonitor.yosrahibrahim.com.instamonitorlib.database.InstaMonitorDatabaseHelper;
import instamonitor.yosrahibrahim.com.instamonitorlib.models.InstaSession;

/**
 * Created by Yosrah Ibrahim on 6/9/2016.
 *
 *
 * Table model for InstaSession
 */
public class InstaSessionTable extends AbstractTable<InstaSession> {

    /**
     * Table name
     */
    public static final String TABLE_NAME = "session";

    /**
     * Column for id
     */
    public static final String COLUMN_ID = "_id";

    /**
     * Column for component name
     */
    public static final String COLUMN_NAME = "name";

    /**
     * Column for duration of time spent in milliseconds
     */
    public static final String COLUMN_DURATION = "duration";

    /**
     * Column for if component time is ignored in overall app time calculation
     */
    public static final String COLUMN_IS_IGNORED = "isIgnored";

    /**
     * Column for component type
     */
    public static final String COLUMN_COMPONENT_TYPE = "componentType";


    /**
     * Create table statement
     */
    public static final String CREATE_TABLE = "CREATE TABLE  " + TABLE_NAME
            + "(" + COLUMN_ID + " integer NOT NULL primary key autoincrement, "
            + COLUMN_NAME + " text NOT NULL," + COLUMN_DURATION + " integer," + COLUMN_IS_IGNORED + " boolean," + COLUMN_COMPONENT_TYPE + " integer);";


    private static InstaSessionTable instance;

    /**
     *
     * return singleton for InstaSession table
     *
     * @param context
     * @return InstaSessionTable
     */
    public static InstaSessionTable getInstance(Context context) {
        if (instance == null)
            instance = new InstaSessionTable(InstaMonitorDatabaseHelper.getInstance(context), context);
        return instance;
    }

    public InstaSessionTable(SQLiteOpenHelper openHelper, Context context) {
        super(openHelper, context);
    }


    /**
     * executes CREATE_TABLE sql_statement on initialization
     * @param db
     */
    @Override
    public void create(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    /**
     * DROPS old table data on any upgrade to the db version
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void upgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        create(db);
    }

    /**
     * @return TABLE_NAME
     */
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    /**
     * @return projection for table columns
     */
    @Override
    public String[] getProjection() {
        String[] columns = { COLUMN_ID, COLUMN_NAME, COLUMN_COMPONENT_TYPE, COLUMN_DURATION, COLUMN_IS_IGNORED };

        return columns;
    }


    /**
     * gets content value for an InstaSession object
     *
     * @param session
     * @return ContentValues
     */
    @Override
    public ContentValues getContentValues(InstaSession session) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, session.getName());
        values.put(COLUMN_DURATION,session.getDuration() );
        values.put(COLUMN_COMPONENT_TYPE, session.getComponentType());
        values.put(COLUMN_IS_IGNORED, session.isIgnored());

        return values;
    }

    /**
     * return InstaSession object from cursor
     *
     * @param cursor
     * @return InstaSession
     */
    @Override
    public InstaSession getObjFromCursor(Cursor cursor) {

        InstaSession session = new InstaSession();

        session.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        session.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        session.setDuration(cursor.getInt(cursor.getColumnIndex(COLUMN_DURATION)));
        session.setComponentType(cursor.getInt(cursor.getColumnIndex(COLUMN_COMPONENT_TYPE)));
        session.setIgnored(convertToBoolean(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_IGNORED))));

        return session;
    }


    /**
     * return sum of time in millisecond spent on all components excluding those that
     * are set to be ignored
     *
     * @return sum
     */
    public int getSumOfDuration(){

        Cursor cursor = getDb().rawQuery("select SUM(" + COLUMN_DURATION + ") from "+ getTableName()+ " where "+ COLUMN_IS_IGNORED + "= 0 and "+ COLUMN_COMPONENT_TYPE + "= " + InstaSession.TYPE_ACTIVITY, null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        cursor.close();

        return count;
    }

    /**
     * return InstaSession object for the corresponding component
     *
     * @param componentName
     * @return InstaSession
     */
    public InstaSession getSession(String componentName){

        InstaSession session = null;

        Cursor cursor = getDb().rawQuery("select * from "+ getTableName()+ " where "+ COLUMN_NAME + "=  '" + componentName + "'", null);
        if(cursor.moveToFirst()){
            session = getObjFromCursor(cursor);
        }

        cursor.close();

        return session;
    }

    /**
     * update isIgnored column for corresponding session
     *
     * @param session
     * @return updated
     */
    public boolean updateIsIgnored(InstaSession session) {

        return getDb().update(getTableName(), getContentValues(session), COLUMN_NAME + " = ?", new String[] { session.getName() }) > 0;
    }


    /**
     * update duration of time for corresponding session
     *
     * @param session
     * @return updated
     */
    public boolean updateDuration(InstaSession session){

        InstaSession oldSession = getSession(session.getName());

        if(oldSession != null){
           session.setDuration(oldSession.getDuration() + session.getDuration());
        }

        return getDb().update(getTableName(), getContentValues(session), COLUMN_NAME + " = ?", new String[] { session.getName() }) > 0;
    }

}
