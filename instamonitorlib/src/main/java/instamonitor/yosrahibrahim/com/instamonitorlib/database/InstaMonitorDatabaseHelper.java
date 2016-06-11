package instamonitor.yosrahibrahim.com.instamonitorlib.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import instamonitor.yosrahibrahim.com.instamonitorlib.database.tables.AbstractTable;

/**
 * Created by Yosrah Ibrahim on 6/9/2016.
 */
public class InstaMonitorDatabaseHelper extends SQLiteOpenHelper {

    /**
     *  name of the database file
     *  */
    private static final String DATABASE_NAME = "instamonitor.db";

    /**
     *  database version that should be incremented if there are any structural changes
     *  */
    private static final int DATABASE_VERSION = 1;

    /**
     *  List of registered tables
     *  */
    private List<AbstractTable> dbTables;

    private static InstaMonitorDatabaseHelper instance;
    private SQLiteDatabase db;

    /**
     * @param context
     * @return An instance of database helper.
     */
    public synchronized static InstaMonitorDatabaseHelper getInstance(Context context) {
        if (instance == null)
            instance = new InstaMonitorDatabaseHelper(context);
        return instance;
    }

    public InstaMonitorDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbTables = new ArrayList<AbstractTable>();
    }


    /**
     * execite CREATE_TABLE statement for registered tables
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            for (AbstractTable table : dbTables){
                table.create(db);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            for (AbstractTable table : dbTables) {
                dropTable(db, table.getTableName());
            }

            onCreate(db);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropTable(SQLiteDatabase db, String table) {
        db.execSQL("DROP TABLE IF EXISTS " + table + ";");
    }

    public void clearDatabase() {
        for (AbstractTable table : dbTables)
            table.deleteAll();
    }

    public void addTable(AbstractTable table) {
        dbTables.add(table);
    }
}
