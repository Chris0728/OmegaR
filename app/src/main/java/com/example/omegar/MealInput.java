package com.example.omegar;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.omegar.NonActivityClasses.Meal;


public class MealInput extends AppCompatActivity {
    private final double defaultVal = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_input2);

        Intent mealIntent = this.getIntent();
        Meal meal = (Meal) mealIntent.getSerializableExtra("MEAL");

        final Button backButton = findViewById(R.id.back); //change to button ID
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
            }
        });
    }


}
