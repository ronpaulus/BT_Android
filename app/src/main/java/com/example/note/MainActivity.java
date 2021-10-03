package com.example.note;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    TextView top_title_txt, top_content_txt;

    DBHelper myDB;
    ArrayList<String> note_title, note_content, note_date;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);

        top_title_txt = findViewById(R.id.top_title_txt);
        top_content_txt = findViewById(R.id.top_content_txt);

        //nhận dữ liệu
        Intent intent = getIntent();
        String title1 = intent.getStringExtra("title");
        String content1 = intent.getStringExtra("content");

        top_title_txt.setText(title1);
        top_content_txt.setText(content1);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new DBHelper(MainActivity.this);
        note_title = new ArrayList<>();
        note_content = new ArrayList<>();
        note_date = new ArrayList<>();

        storeDataInArrays();
        customAdapter = new CustomAdapter(MainActivity.this, note_title, note_content, note_date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                note_title.add(cursor.getString(1));
                note_content.add(cursor.getString(2));
                note_date.add(cursor.getString(3));
            }
        }
    }


}