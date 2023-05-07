package com.example.project_sub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

public class Asset extends AppCompatActivity {
    SQLiteDatabase db;
    Button btn;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_main);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Homify-Assets");
        ab.setBackgroundDrawable(new ColorDrawable(Color.rgb(128, 123, 227)));
        ab.hide();
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
                    textViewNew.setText(cursor.getString(0)+"          " + cursor.getString(1));
                    textViewNew.setTextSize(20f);
                    textViewNew.setTypeface(Typeface.DEFAULT_BOLD);
                    textViewNew.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
                    textViewNew.setPadding(0, 20, 0, 20);
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
                    cv.addView(textViewNew);
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