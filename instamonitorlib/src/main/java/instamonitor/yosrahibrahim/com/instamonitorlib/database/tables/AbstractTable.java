package instamonitor.yosrahibrahim.com.instamonitorlib.database.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import instamonitor.yosrahibrahim.com.instamonitorlib.database.InstaMonitorDatabaseHelper;

/**
 * Created by Yosrah Ibrahim on 6/9/2016.
 *
 * Generic class for databse tables
 */
public abstract class AbstractTable<T> {

    private SQLiteDatabase db;
    private SQLiteOpenHelper openHelper;

    public AbstractTable(SQLiteOpenHelper openHelper, Context context) {
        InstaMonitorDatabaseHelper.getInstance(context).addTable(this);
        this.openHelper = openHelper ;
    }


    public SQLiteDatabase getDb() {
        if (db == null)
            db = openHelper.getWritableDatabase();
        return db;
    }

    public T get(int id) {
        Cursor cursor = getDb().query(getTableName(), getProjection(), BaseColumns._ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);

        T obj = null;
        if (cursor.moveToFirst())
            obj = getObjFromCursor(cursor);

        return obj;
    }

    public List<T> getBySelection(String selection, String[] selectionArgs) {
        return getList(selection, selectionArgs);
    }

    public List<T> getAll(String selection, String[] selectionArgs) {
        return getList(selection, selectionArgs);
    }

    private List<T> getList(String selection, String[] selectionArgs) {
        Cursor cursor = getDb().query(getTableName(), getProjection(), selection, selectionArgs, null, null, null);

        List<T> list = new ArrayList<T>(cursor.getCount() >= 0 ?
                cursor.getCount() : 10);
        if (cursor.moveToFirst()) {
            do {
                list.add(getObjFromCursor(cursor));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public boolean insert(T obj) {
        return getDb().insert(getTableName(), null, getContentValues(obj)) != -1;
    }

    public void insert(List<T> objList) {
        for (T obj : objList) {
            insert(obj);
        }
    }

    public boolean update(T obj, int id) {
        return getDb().update(getTableName(), getContentValues(obj), BaseColumns._ID + " = ?",
                new String[] { String.valueOf(id) }) > 0;
    }

    public void delete(int id) {
        getDb().delete(getTableName(), BaseColumns._ID + " = ?", new String[] { String.valueOf(id) });
    }

    public void delete(String selection, String[] selectionArgs) {
        getDb().delete(getTableName(), selection, selectionArgs);
    }

    public void deleteAll() {
        getDb().delete(getTableName(), null, null);
    }

    public boolean convertToBoolean(int i){

        if(i == 1){
            return true;
        }else{
            return false;
        }
    }


    public abstract void create(SQLiteDatabase db);

    public abstract void upgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    public abstract String getTableName();

    public abstract String[] getProjection();

    public abstract ContentValues getContentValues(T entity);

    public abstract T getObjFromCursor(Cursor cursor);
}
