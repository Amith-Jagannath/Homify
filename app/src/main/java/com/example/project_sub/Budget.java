package com.example.project_sub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Budget extends AppCompatActivity {
    SQLiteDatabase db;
    int curBudget;
    String budgetVal;
    private FloatingActionButton button;
    private ArrayList<Expense> expenseList;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ExpenseAdapter expenseAdapter;
Button change;
int total =0;
TextView budget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        button = findViewById(R.id.addExpenseBtn);
        db = new DataBaseHelper(this,"Expense").getWritableDatabase();
        expenseList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        change = findViewById(R.id.change);
        budget = findViewById(R.id.budget);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddMonthlyExp.class);
                startActivity(i);
            }
        });






        Intent in = getIntent();
        budget.setText(in.getStringExtra("res"));
        setupRecyclerView();
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddExpenseItem.class);
            startActivity(intent);
        });
        //****************************** accessing Budget
        Cursor cursor2 = db.rawQuery("SELECT * FROM Budget", null);
        for(int i=0;i<cursor2.getCount();i++) {
            cursor2.moveToNext();
            budgetVal = cursor2.getString(1);
            Log.d("month:",cursor2.getString(0));
            Log.d("budget:",cursor2.getString(1));
        }
        //Log.e("Data:",budgetVal);


// *****************************************access expenses
        Cursor cursor = db.rawQuery("select * from Expense ", null);

       // int deletedRows = db.delete("Expense", "day" + " IS NULL", null);
        if (cursor.getCount() <= 0) {
            Toast.makeText(getApplicationContext(), "No records found", Toast.LENGTH_SHORT).show();
            AppCompatTextView b = findViewById(R.id.expenseBtn);

           b.setText("No Expense found");

        } else {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String s = "";
                String day = cursor.getString(0);
              String amount = cursor.getString(1);
               total += Integer.parseInt(amount);

              Log.i("total", String.valueOf(total));
               expenseList.add(new Expense(day, amount));
            }
             if(budgetVal!=null) {
                 curBudget = Integer.parseInt(budgetVal) - total;
                 budget.setText(Integer.toString(curBudget));
             }






            expenseAdapter = new ExpenseAdapter(expenseList);
            expenseAdapter.notifyDataSetChanged();
        }


   //*******************access budget for a month



    }


    private void setupRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        expenseAdapter = new ExpenseAdapter(expenseList);
        recyclerView.setAdapter(expenseAdapter);
    }
}