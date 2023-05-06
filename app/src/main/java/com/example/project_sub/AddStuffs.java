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

import com.example.project_sub.DataBaseHelper;

public class AddStuffs extends AppCompatActivity {
    SQLiteDatabase db;
    EditText name;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stuffs);
        db = new DataBaseHelper(this,"Items").getWritableDatabase();
        name = findViewById(R.id.itemName);
        btn = findViewById(R.id.addStuffsBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String stuff_name;
                stuff_name = name.getText().toString();

                if (stuff_name.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Stuffs", Toast.LENGTH_SHORT).show();
                } else {

                    Cursor cursor = db.rawQuery("select * from Items ", null);
                    int id = cursor.getCount();
                    ContentValues values = new ContentValues();
                    values.put("name",stuff_name);
                    values.put("id",id+1);
                    db.insert("Items", null, values);

                    Intent i = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(i);

                }




            }
        });

    }
}