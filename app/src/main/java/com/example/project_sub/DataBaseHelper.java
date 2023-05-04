package com.example.project_sub;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DataBaseHelper extends SQLiteOpenHelper {
    public static String DB_name = "Expenses_DB";
    public DataBaseHelper(Context context) {
        super(context, DB_name, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Expense(day primary key,expense)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if exist
        //db.execSQL("DROP TABLE IF EXISTS student" );
        // Create tables again
        onCreate(db);
    }
}
