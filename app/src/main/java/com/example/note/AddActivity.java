package com.example.note;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText title_input, content_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        final String currentDate = df.format(Calendar.getInstance().getTime());
        title_input = findViewById(R.id.title_input);
        content_input = findViewById(R.id.content_input);
//        date_input = findViewById(R.id.date_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(AddActivity.this);
                dbHelper.addNote(title_input.getText().toString().trim(), content_input.getText().toString().trim(), currentDate.toString().trim());
            }
        });
    }
}