package com.shadrackngotho.apps.fleetmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SHADRACK NGOTHO on 9/26/2016.
 */
public class kavDBHelper extends SQLiteOpenHelper {

    private int mYear, mMonth, mDay;

    public static final String DATABASE_NAME = "KAV.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TRIPS_TABLE_NAME = "trips";
    public static final String TRIPS_COLUMN_ID= "_id";
    public static final String TRIPS_COLUMN_DATE = "date";
    public static final String TRIPS_COLUMN_JOURNEY = "journey";
    public static final String TRIPS_COLUMN_FUEL = "fuel";
    public static final String TRIPS_COLUMN_PAYMENTS = "payments";
    public static final String TRIPS_COLUMN_ALLOWANCES = "allowances";
    public static final String TRIPS_COLUMN_MISCELLANEOUS  = "miscellaneous";


    public kavDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TRIPS_TABLE_NAME +
                        "(" + TRIPS_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        TRIPS_COLUMN_DATE + " TEXT, " +
                        TRIPS_COLUMN_JOURNEY + " TEXT, " +
                        TRIPS_COLUMN_FUEL + " INTEGER, " +
                        TRIPS_COLUMN_PAYMENTS + " INTEGER, " +
                        TRIPS_COLUMN_ALLOWANCES + " INTEGER, " +
                        TRIPS_COLUMN_MISCELLANEOUS + " INTEGER)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TRIPS_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertTrip(String date, String journey, int fuel, int payments, int allowances, int miscellaneous){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRIPS_COLUMN_DATE, date);
        contentValues.put(TRIPS_COLUMN_JOURNEY, journey);
        contentValues.put(TRIPS_COLUMN_FUEL, fuel);
        contentValues.put(TRIPS_COLUMN_PAYMENTS, payments);
        contentValues.put(TRIPS_COLUMN_ALLOWANCES, allowances);
        contentValues.put(TRIPS_COLUMN_MISCELLANEOUS, miscellaneous);

        db.insert(TRIPS_TABLE_NAME , null , contentValues);
        return true;
    }
    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TRIPS_TABLE_NAME);
        return numRows;
    }
    public boolean updateTrip(Integer id, String date, String journey, Integer fuel, Integer payments, Integer allowances, Integer miscellaneous) {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRIPS_COLUMN_DATE, date);
        contentValues.put(TRIPS_COLUMN_JOURNEY, journey);
        contentValues.put(TRIPS_COLUMN_FUEL, fuel);
        contentValues.put(TRIPS_COLUMN_PAYMENTS, payments);
        contentValues.put(TRIPS_COLUMN_ALLOWANCES, allowances);
        contentValues.put(TRIPS_COLUMN_MISCELLANEOUS, miscellaneous);
        db.update(TRIPS_TABLE_NAME, contentValues, TRIPS_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public Integer deleteTrip(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TRIPS_TABLE_NAME,
                TRIPS_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }
    public Cursor getTrip(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + TRIPS_TABLE_NAME + " WHERE " +
                TRIPS_COLUMN_ID + "=?", new String[]{Integer.toString(id)});
        return res;
    }
    public Cursor getAllTrips() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + TRIPS_TABLE_NAME, null );
        return res;
    }
}