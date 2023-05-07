package com.example.project_sub;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Contact extends AppCompatActivity {

    FloatingActionButton add1;
    SearchView sv;
    ListView lv;
    ArrayList<String> contacts;
    ArrayAdapter<String> con;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        db = new ContactsDB(this).getWritableDatabase();

        lv = findViewById(R.id.lv);
        sv = findViewById(R.id.search);
        add1 = findViewById(R.id.addcontactbutton);


        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddContact.class);
                startActivity(i);
            }
        });


        Cursor c = db.rawQuery("SELECT * FROM contact",null);
        contacts = new ArrayList<>();
        if(c.getCount()<=0){
            Toast.makeText(this, "nop", Toast.LENGTH_SHORT).show();
        }
        else{
            for(int i=0;i<c.getCount();i++){
                c.moveToNext();
                contacts.add(c.getString(0));
                Toast.makeText(Contact.this, "Name: "+c.getString(0), Toast.LENGTH_SHORT).show();
            }
            con = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,contacts);
            lv.setAdapter(con);
        }

        c.close();

//        contacts = new ArrayList<>();
//
//        contacts.add("Monday");
//        contacts.add("Tuesday");
//        con = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,contacts);
//        lv.setAdapter(con);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Cursor c1 = db.rawQuery("SELECT * from contact where name ='"+lv.getItemAtPosition(i).toString()+"'",null);
                c1.moveToNext();
                System.out.println(c1.getString(1));
                Toast.makeText(Contact.this, c1.getString(1), Toast.LENGTH_SHORT).show();
                Intent callintent =new Intent(Intent.ACTION_CALL);
                if(c1.getCount()>=0) {

                    callintent.setData(Uri.parse("tel:" + c1.getString(1)));
                    try {
                        startActivity(callintent);
                    } catch (Exception e) {
                        Log.d("error", e.getMessage());
                        Toast.makeText(getApplicationContext(), "call denied", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(Contact.this, lv.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                    c1.close();
                }

            }
        });

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                con.getFilter().filter(s);
                return false;
            }
        });
    }
}