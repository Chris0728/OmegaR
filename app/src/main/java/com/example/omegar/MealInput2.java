package com.example.omegar;

import android.content.Intent;
import android.content.res.AssetManager;
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
import com.example.omegar.NonActivityClasses.food;
import com.example.omegar.NonActivityClasses.foodArray;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


public class MealInput2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_input2);

        // Get a reference to the AutoCompleteTextView in the layout
        final AutoCompleteTextView foodNameInput = findViewById(R.id.autoCompleteTextView2);
        // Get the string array
        String[] countries = getResources().getStringArray(R.array.meal_names);
        final ArrayList<food> converted;
try {
    InputStream input = getAssets().open("nutrient_database/food_api.json");
    int size = input.available();
    byte[] buffer = new byte[size];
    input.read();
    input.close();

    String unconverted = new String(buffer,"UTF-8");


    JSONArray jarray = new JSONArray(unconverted);
    converted = new Gson().fromJson(jarray.toString(), ArrayList.class);
}catch(IOException e){
    e.printStackTrace();
} catch (JSONException e) {
    e.printStackTrace();
}


        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        foodNameInput.setAdapter(adapter);

        final EditText foodWeightInput = findViewById(R.id.editText6);

        final Button mealInputButton = findViewById(R.id.input_meal); //change to button ID
        mealInputButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //For Demo Only
                Meal meal = null;

                /*
                String mealName = "";
                double omega3 = 0, omega6 = 0;
                */

                double amount = Double.parseDouble(foodWeightInput.getText().toString());

                switch(foodNameInput.getText().toString().toUpperCase()){
                    case "FRENCH FRIES":
                        meal = new Meal("French Fries", 1, 10, amount);
                        /*
                        mealName = "French Fries";
                        omega3 = 5;
                        omega6 = 2;
                         */
                        break;
                    case "GROUND BEEF":
                        meal = new Meal("Ground Beef", 3, 40, amount);
                        /*
                        mealName = "Ground Beef";
                        omega3 = 3;
                        omega6 = 4;
                         */
                        break;
                    case "RICE":
                        meal = new Meal("Rice", 10, 1, amount);
                        /*
                        mealName = "Rice";
                        omega3 = 1;
                        omega6 = 2;
                         */
                        break;
                    default:
                        break;

                }
                Homepage.meals.addMeal(meal);
                Intent mealIntent = new Intent(getBaseContext(), Homepage.class);

                //mealIntent.putExtra("MEAL", meal);
                /*
                mealIntent.putExtra("MEAL_NAME", mealName);
                mealIntent.putExtra("OMEGA_3", omega3);
                mealIntent.putExtra("OMEGA_6", omega6);
                mealIntent.putExtra("AMOUNT", amount);
                */

                startActivity(mealIntent);

            }
        });

        final Button backButton = findViewById(R.id.back); //change to button ID
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MealInput2.super.onBackPressed();
            }
        });





    }


}