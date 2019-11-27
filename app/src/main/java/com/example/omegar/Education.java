package com.example.omegar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Education extends AppCompatActivity {

    private TextView text;
    private Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        text = findViewById(R.id.sampleText);


        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Education.super.onBackPressed();
            }
        });
    }
}
