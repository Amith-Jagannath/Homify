package com.example.project_sub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpenseItem extends AppCompatActivity {
    SQLiteDatabase db;
    String exp = "Expenses";
    Button btn;
    EditText expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense_item);
        db = new DataBaseHelper(this,"Expense").getWritableDatabase();
        btn = findViewById(R.id.addExpenseBtn);
        expense = findViewById(R.id.addExpenseEditText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exp;
                exp = expense.getText().toString();

                if (exp.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Expense", Toast.LENGTH_SHORT).show();
                } else {

                    Cursor cursor = db.rawQuery("select * from Expense ", null);
                    int day = cursor.getCount();
                    ContentValues values = new ContentValues();
                    values.put("expense", exp);
                   values.put("day",day+1);
                    db.insert("Expense", null, values);

                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    //i.putExtra("res","Expense Added");
                    startActivity(i);

                }

            }
        });
    }
}