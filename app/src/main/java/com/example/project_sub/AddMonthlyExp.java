package com.example.project_sub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddMonthlyExp extends AppCompatActivity {
SQLiteDatabase db;
Button btn;
TextView budget;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_monthly_exp);
        db = new DataBaseHelper(this,"Budget").getWritableDatabase();
        budget = findViewById(R.id.addMonthlyExp);
        btn = findViewById(R.id.addMonthlyBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ContentValues values = new ContentValues();
                values.put("budget", budget.getText().toString());
                String whereClause = "month=?";
                String[] whereArgs = {"1"};
                db.update("Budget", values, whereClause, whereArgs);*/


                Cursor cursor = db.rawQuery("select * from Budget ", null);
                int month = cursor.getCount();
                ContentValues values = new ContentValues();
                values.put("budget", budget.getText().toString());
                values.put("month",month);
                db.insert("Budget", null, values);

                // resetting the content of expense since the budget has changed
                db.execSQL("DELETE FROM Expense;");
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("res",budget.getText().toString());
                startActivity(i);
            }
        });


    }



}