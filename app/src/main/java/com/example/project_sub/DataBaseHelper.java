package com.example.project_sub;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DataBaseHelper extends SQLiteOpenHelper {
    public static String DB_name = "Expenses_DB";
    public DataBaseHelper(Context context) {
        super(context, DB_name, null, 3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Budget(month primary key,budget)");
        db.execSQL("create table Expense(day primary key,expense)");
        db.execSQL("create table Items(id primary key,name)");
        db.execSQL("create table Asset(name,expiryDate)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS Expense" );
        db.execSQL("DROP TABLE IF EXISTS Budget" );
        db.execSQL("DROP TABLE IF EXISTS Items" );
        // Create tables again
        onCreate(db);
    }

    public boolean isTableExists(SQLiteDatabase db, String tableName) {
        boolean tableExists;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 0", null);
            tableExists = true;
        } catch (Exception e) {
            return false;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return tableExists;
    }
}

