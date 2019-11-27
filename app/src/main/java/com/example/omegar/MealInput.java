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

    private Button backBtn;
    private Button breakfastBtn;
    private Button lunchBtn;
    private Button dinnerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_input);

        //Intent mealIntent = this.getIntent();
        //Meal meal = (Meal) mealIntent.getSerializableExtra("MEAL");

        //Button Init
        this.backBtn = findViewById(R.id.back); //change to button ID
        this.breakfastBtn = findViewById(R.id.breakfast);
        this.lunchBtn = findViewById(R.id.lunch);
        this.dinnerBtn = findViewById(R.id.dinner);

        //Button Setup
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MealInput.super.onBackPressed();
            }
        });

        breakfastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent nextAct = new Intent(getBaseContext(), MealInput2.class);
                startActivity(nextAct);
            }
        });

        lunchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent nextAct = new Intent(getBaseContext(), MealInput2.class);
                startActivity(nextAct);
            }
        });

        dinnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent nextAct = new Intent(getBaseContext(), MealInput2.class);
                startActivity(nextAct);
            }
        });
    }


}
