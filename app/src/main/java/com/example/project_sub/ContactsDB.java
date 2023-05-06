package com.example.project_sub;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactsDB extends SQLiteOpenHelper {

    public static String DB_name = "CONTACT_DB";

    public ContactsDB(Context context) {
        super(context, DB_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE contact(name,phone primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(sqLiteDatabase);
    }
}