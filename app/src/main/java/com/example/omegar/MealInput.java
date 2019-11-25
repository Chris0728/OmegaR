package com.example.omegar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_input2);

        // Get a reference to the AutoCompleteTextView in the layout
        final AutoCompleteTextView foodNameInput = findViewById(R.id.autoCompleteTextView2);
        // Get the string array
        String[] countries = getResources().getStringArray(R.array.meal_names);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        foodNameInput.setAdapter(adapter);

        final EditText foodWeightInput = findViewById(R.id.editText6);

        final Button mealInputButton = findViewById(R.id.input_meal); //change to button ID
        mealInputButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //For Demo Only
                Meal meal;
                double amount = Double.parseDouble(foodWeightInput.getText().toString());

                switch(foodNameInput.getText().toString().toUpperCase()){
                    case "FRENCH FRIES":
                        meal = new Meal("French Fries", 2, 5, amount);
                        break;
                    case "GROUND BEEF":
                        meal = new meal("Ground Beef", 3, 4, amount);
                        break;
                    case "RICE":
                        meal = new meal("Rice", 1, 2, amount);
                        break;
                    default:
                        break;

                }
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
