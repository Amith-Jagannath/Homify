package com.example.project_sub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.project_sub.AddExpenseItem;
import com.example.project_sub.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    private FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.addExpenseBtn);
        db = new DataBaseHelper(this).getWritableDatabase();
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddExpenseItem.class);
            startActivity(intent);
        });

        Cursor cursor = db.rawQuery("select * from Expense ", null);
        if (cursor.getCount() <= 0) {
            Toast.makeText(getApplicationContext(), "No records found",
                    Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String s = "";
                s += "Day" + cursor.getString(0);
                s += "Expense" + cursor.getString(1);

                Log.e("Data", s);


            }
        }
    }
}