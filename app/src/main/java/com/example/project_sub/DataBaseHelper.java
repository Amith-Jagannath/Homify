package com.example.project_sub;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DataBaseHelper extends SQLiteOpenHelper {
    public static String DB_name;
    public DataBaseHelper(Context context,String DB_name) {
        super(context, DB_name, null, 1);
        this.DB_name = DB_name;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DB_name+"(name,expiryDate)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS "+DB_name );
        // Create tables again
        onCreate(db);
    }

    public boolean isTableExists(SQLiteDatabase db, String tableName) {
        boolean tableExists = false;
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

