package com.example.omegar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Education extends AppCompatActivity {

    private Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);


        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Education.super.onBackPressed();
            }
        });
    }
}
