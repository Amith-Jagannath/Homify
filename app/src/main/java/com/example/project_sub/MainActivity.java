package com.example.project_sub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.project_sub.AddExpenseItem;
import com.example.project_sub.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    private FloatingActionButton button;
    private ArrayList<Expense> expenseList;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ExpenseAdapter expenseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.addExpenseBtn);
        db = new DataBaseHelper(this).getWritableDatabase();
        expenseList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);

        setupRecyclerView();
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
                String day = cursor.getString(0);
                String amount = cursor.getString(1);

                Log.e("Data", s);
                expenseList.add(new Expense(day, amount));
            }

            expenseAdapter = new ExpenseAdapter(expenseList);
            expenseAdapter.notifyDataSetChanged();
        }
    }


    private void setupRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        expenseAdapter = new ExpenseAdapter(expenseList);
        recyclerView.setAdapter(expenseAdapter);
    }
}