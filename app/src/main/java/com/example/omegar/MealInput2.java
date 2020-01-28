package com.example.omegar;

import android.content.Intent;
import android.content.res.AssetManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.omegar.NonActivityClasses.AutocompleteFoodAdapter;
import com.example.omegar.NonActivityClasses.Meal;
import com.example.omegar.NonActivityClasses.food;
import com.example.omegar.NonActivityClasses.foodArray;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        ArrayList<food> converted = new ArrayList<>();
        String error = "";
    try {
        AssetManager assetManager = this.getAssets();
        InputStream input = assetManager.open("nutrient_database/food_api.json");
        int size = input.available();
        byte[] buffer = new byte[size];
        input.read(buffer);
        input.close();

        String unconverted = new String(buffer,"utf-8");

        JSONArray jarray = new JSONArray(unconverted);

        for(int i =0; i < jarray.length();i++){
           JSONObject obj = jarray.getJSONObject(i);
            String code = obj.getString("food_code");
            String description = obj.getString("food_description");
            food foodI = new food(code, description);
            converted.add(foodI);
        }
    }catch(IOException e){
        Log.e("Chris", Log.getStackTraceString(e));

    } catch (JSONException e) {
        Log.e("IGAT ", Log.getStackTraceString(e));

    }

        //Toast.makeText(MealInput2.this,error,Toast.LENGTH_LONG).show();

        // Create the adapter and set it to the AutoCompleteTextView
        //ArrayAdapter<String> adapter =
                //new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        AutocompleteFoodAdapter adapter = new AutocompleteFoodAdapter(this, converted);
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

                switch(foodNameInput.getText().toString()){
                    case "Chicken, broiler, giblets, raw":
                        meal = new Meal("Chicken, broiler, giblets, raw", 1, 10, amount);
                        /*
                        mealName = "Chicken, broiler, giblets, raw";
                        omega3 = 1;
                        omega6 = 10;
                         */
                        break;
                    case "Chicken, broiler, giblets, flour coated, fried":
                        meal = new Meal("Chicken, broiler, giblets, flour coated, fried", 3, 40, amount);
                        /*
                        mealName = "Chicken, broiler, giblets, flour coated, fried";
                        omega3 = 3;
                        omega6 = 40;
                         */
                        break;
                    case "Chicken, broiler, giblets, simmered":
                        meal = new Meal("Chicken, broiler, giblets, simmered", 10, 1, amount);
                        /*
                        mealName = "Chicken, broiler, giblets, simmered";
                        omega3 = 10;
                        omega6 = 1;
                         */
                        break;
                    default:
                        break;

                }
                Homepage.meals.addMeal(meal);
                Intent mealIntent = new Intent(getBaseContext(), Homepage.class);

                mealIntent.putExtra("MEAL", meal);
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