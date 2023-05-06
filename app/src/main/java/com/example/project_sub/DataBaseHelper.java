package com.example.project_sub;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
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
}
