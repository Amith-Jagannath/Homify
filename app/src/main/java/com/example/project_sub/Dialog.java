package com.example.project_sub;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Dialog extends AppCompatActivity {
    SQLiteDatabase db;
    Button ok;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);

        // Find the EditText views in the dialog layout
        EditText editText = findViewById(R.id.et);
        DatePicker datePicker = findViewById(R.id.date);
        db = new DataBaseHelper(this).getWritableDatabase();

        // Set a click listener for the OK button in the dialog
        ok = findViewById(R.id.button2);
        ok.setOnClickListener( view -> {
            // Get the user input from the EditText views
            String name = editText.getText().toString();
            String date = datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear();

            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("expiryDate", date);
            db.insert("Asset", null, values);
            Intent i = new Intent(getApplicationContext(), Asset.class);
            startActivity(i);
        });

        // Set a click listener for the Cancel button in the dialog
        Button cancelButton = findViewById(R.id.button3);
        cancelButton.setOnClickListener(view -> {
            Intent i =new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        });
    }
}
