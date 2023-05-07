package com.example.project_sub;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddContact extends AppCompatActivity {

    EditText name,phone;
    Button save;
    SQLiteDatabase db;
    FloatingActionButton delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar ab = getSupportActionBar();
        ab.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        phone = findViewById(R.id.phoneNo);
        name = findViewById(R.id.name);
        save = findViewById(R.id.add);
        delete = findViewById(R.id.del);
        db = new ContactsDB(this).getWritableDatabase();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("name",name.getText().toString());
                values.put("phone",phone.getText().toString());
                db.insert("contact",null,values);
                Toast.makeText(AddContact.this, "Inserted", Toast.LENGTH_SHORT).show();
                Intent inten = new Intent(getApplicationContext(),Contact.class);
                startActivity(inten);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("delete from contact where name='" +name.getText().toString()+"'");
                db.close();
                Intent inten = new Intent(getApplicationContext(),Contact.class);
                startActivity(inten);
            }
        });
    }
}