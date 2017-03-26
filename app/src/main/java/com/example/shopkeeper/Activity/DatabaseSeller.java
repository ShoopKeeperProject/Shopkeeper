package com.example.shopkeeper.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 26/3/2017.
 */
public class DatabaseSeller extends SQLiteOpenHelper    {
    public static final String DATABASE_NAME = "Seller.db";
    public static final String TABLE_NAME = "Seller_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "AMOUNT";
    public DatabaseSeller(Context context) {
        super(context, DATABASE_NAME, null, 1); // super(context, name, factor, version
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT, AMOUNT INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    // Method to insert a record to the database
    public boolean insertData(String username, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,amount);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

}