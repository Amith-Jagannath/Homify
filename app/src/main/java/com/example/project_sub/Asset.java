package com.example.project_sub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Asset extends AppCompatActivity {
    SQLiteDatabase db;
    Button btn;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_main);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        ImageButton home = findViewById(R.id.h);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        ll=findViewById(R.id.linearLayout);
        btn = findViewById(R.id.button);
        db = new DataBaseHelper(this).getWritableDatabase();
        DataBaseHelper dbtest = new DataBaseHelper(this);
        if(dbtest.isTableExists(db,"Asset")) {
            Cursor cursor = db.rawQuery("select * from Asset ", null);
            if (cursor.getCount() == -1) {
                Toast.makeText(getApplicationContext(), "Assets not found!", Toast.LENGTH_SHORT).show();
            } else{
                while(cursor.moveToNext()) {
                    CardView cv = new CardView(getApplicationContext());
                    TextView textViewNew = new TextView(this);
                    String name = cursor.getString(0);
                    textViewNew.setText(name+"          " + cursor.getString(1));
                    textViewNew.setTextSize(20f);
                    textViewNew.setTypeface(Typeface.DEFAULT_BOLD);
                    textViewNew.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
                    textViewNew.setPadding(0, 20, 0, 20);
                    ImageButton btn = new ImageButton(this);
                    btn.setBackground(getResources().getDrawable(R.drawable.baseline_delete_24));
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            db.execSQL("delete from Asset where name= '"+name+"'");
                            Intent i = new Intent(getApplicationContext(),Asset.class);
                            startActivity(i);
                        }
                    });
                    cv.setCardElevation(10f);
                    cv.setUseCompatPadding(false);
                    cv.setCardBackgroundColor(Color.WHITE);
                    cv.setMaxCardElevation(12f);
                    cv.setPreventCornerOverlap(true);
                    cv.setBackground(new ColorDrawable(Color.CYAN));
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    layoutParams.setMargins(0, 16, 0,16);
                    cv.setLayoutParams(layoutParams);
                    LinearLayout layout = new LinearLayout(this);
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams layoutParamsnew = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                    textViewNew.setLayoutParams(layoutParamsnew);
                    layout.addView(textViewNew);
                    layout.addView(btn);
                    cv.addView(layout);
                    ll.addView(cv);
                }
            }
        }
        btn.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), Dialog.class);
            startActivity(i);
        });
    }
}