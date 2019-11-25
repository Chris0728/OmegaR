package com.example.omegar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MealInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_input2);

        final Button mealInputButton = findViewById(R.id.input_meal); //change to button ID
        mealInputButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
            }
        });

        final Button backButton = findViewById(R.id.back); //change to button ID
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
            }
        });


    }


}
