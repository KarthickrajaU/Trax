package com.example.trax;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class databasehelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "history.db";
    int db_version = (int) 1.0;
    String table = "data";
    String time = "time";
    String long1 = "long";
    String id = "id";
    String lat = "lat";
    String address = "address";
    String date = "date";
    String ID_COL = "id";
    String DB_PATH;
    private Context context;
    private SQLiteDatabase mydatabase;


    public databasehelper(Context context) {
        super(context, DB_NAME, null, 1);

        this.context = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //String q = "CREATE TABLE " + table + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + id + " TEXT )";
        try {
            String w = "CREATE TABLE \"data\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL,\n" +
                    "\t\"address\"\tTEXT,\n" +
                    "\t\"time\"\tTEXT,\n" +
                    "\t\"date\"\tTEXT,\n" +
                    "\t\"long\"\tTEXT,\n" +
                    "\t\"lat\"\tTEXT,\n" +
                    "\tPRIMARY KEY(\"id\")\n" +
                    ");";
            Log.e("qqqqq", "db create");
            db.execSQL(w);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
    }

    public void addlocation(double longitude1, double latitude1, String s, String currentTime, String currentDate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            //String s1=s.replaceAll(",","\\_");
            db.execSQL("INSERT INTO " + table + " ('" + time + "','" + long1 + "','" + lat + "','" + address + "','" + date + "')" +
                    " VALUES  ('" + currentTime + "','" + longitude1 + "','" + latitude1 + "','" + s + "','" + currentDate + "')");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
