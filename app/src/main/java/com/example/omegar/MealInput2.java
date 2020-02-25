package com.example.omegar;

import android.content.Intent;
import android.content.res.AssetManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.JsonReader;
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
import com.example.omegar.NonActivityClasses.GlobalClass;
import com.example.omegar.NonActivityClasses.Meal;
import com.example.omegar.NonActivityClasses.Meal_nutrient;
import com.example.omegar.NonActivityClasses.food;
import com.example.omegar.NonActivityClasses.foodArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


public class MealInput2 extends AppCompatActivity {
    GlobalClass gloClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_input2);


        gloClass = (GlobalClass)getApplication();
        // Get a reference to the AutoCompleteTextView in the layout
        final AutoCompleteTextView foodNameInput = findViewById(R.id.autoCompleteTextView2);
        // Get the string array

        //String[] countries = getResources().getStringArray(R.array.meal_names);
        final foodArray converted = new foodArray();
        String error = "";
    try {
        //In this try clause, read and parse JSON file "food_api.json" to fill up the autocomplete textView
        //create an assetManager object to get access to "assets" folder in the app
        AssetManager assetManager = this.getAssets();
        //open up an input stream for "food_api.json"
        InputStream input = assetManager.open("nutrient_database/food_api.json");
        int size = input.available();
        byte[] buffer = new byte[size];
        input.read(buffer);
        input.close();

        //converting the unparsed bytes into a string
        String unconverted = new String(buffer, "utf-8");

        //create JSON array with the unparsed string
        JSONArray jarray = new JSONArray(unconverted);

        //extracting JSONObject from JSONArray
        for(int i =0; i < jarray.length();i++){
           JSONObject obj = jarray.getJSONObject(i);
           //get the value of food_code and food_description
            String code = obj.getString("food_code");
            String description = obj.getString("food_description");
            //store the data into a food object/model
            food foodI = new food(code, description);
            //add the food into an arraylist
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
        AutocompleteFoodAdapter adapter = new AutocompleteFoodAdapter(this, converted.array);
        foodNameInput.setAdapter(adapter);

        final EditText foodWeightInput = findViewById(R.id.editText6);

        final Button mealInputButton = findViewById(R.id.input_meal); //change to button ID
        mealInputButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Meal meal = null;
                boolean valid = false;
                double amount;
                /*
                this part is for demo only
                String mealName = "";
                double omega3 = 0, omega6 = 0;
                */

                //Convert the user input into string
                String amountText = foodWeightInput.getText().toString();
                String foodAte = foodNameInput.getText().toString();
                if(Double.parseDouble(amountText.toString())<100){
                    Toast.makeText(MealInput2.this,"Please input more than 100g",Toast.LENGTH_LONG).show();
                    return;
                }
                double n3amount = 0;
                double n6amount = 0;

                //if user entered something for both textViews, do the following
                if(!amountText.equals("")&&!foodAte.equals("")){
                amount = Double.parseDouble(amountText);
                for(Meal_nutrient i : gloClass.getNutrients()){
                    if(i.food_code.equals(converted.getFoodCode(foodAte))){
                        if(i.nutrient_name_id.equals("868")){
                            n3amount += (Double.parseDouble(i.nutrient_value) * amount / 100);
                        }
                        if(i.nutrient_name_id.equals("869")){
                            n6amount += (Double.parseDouble((i.nutrient_value)) * amount / 100);
                        }
                    }
                }
                /*try {
                    AssetManager assetManager = MealInput2.this.getAssets();
                    com.google.gson.stream.JsonReader jsonReader = new com.google.gson.stream.JsonReader(new InputStreamReader(assetManager.open("nutrient_database/nutrient_amount_api.json")));

                    Gson gson = new GsonBuilder().create();
                    jsonReader.beginArray();
                    int count = 0;
                    while(jsonReader.hasNext()){
                        Meal_nutrient nutrient = gson.fromJson(jsonReader, Meal_nutrient.class);
                        if(nutrient.food_code.equals(converted.getFoodCode(foodAte))) {
                            if (nutrient.nutrient_name_id.equals("868")) {
                                n3amount += Double.parseDouble(nutrient.nutrient_value) * amount / 100;
                                count++;
                            }
                            if (nutrient.nutrient_name_id.equals("869")) {
                                n6amount += Double.parseDouble(nutrient.nutrient_value) * amount / 100;
                                count++;
                            }
                        }
                        if(count==2)break;


                    }
                    jsonReader.close();*/
                    //add a new meal
                    Toast.makeText(MealInput2.this,"N3: "+n3amount+ "\n N6: " + n6amount,Toast.LENGTH_LONG).show();
                    meal = new Meal(foodAte,n3amount,n6amount,amount);
                    valid = true;

                /*}catch (UnsupportedEncodingException e){
                    Toast.makeText(MealInput2.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
                catch (FileNotFoundException e){
                    Toast.makeText(MealInput2.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
                catch (IOException e){
                    Toast.makeText(MealInput2.this,e.toString(),Toast.LENGTH_SHORT).show();
                }*/
                //The code below is just for testing
                /*switch(foodNameInput.getText().toString()){
                    case "Chicken, broiler, giblets, raw":
                        meal = new Meal("Chicken, broiler, giblets, raw", 2, 10, amount);
                        valid = true;
                        *//*
                        mealName = "Chicken, broiler, giblets, raw";
                        omega3 = 1;
                        omega6 = 10;
                         *//*
                        break;
                    case "Chicken, broiler, giblets, flour coated, fried":
                        meal = new Meal("Chicken, broiler, giblets, flour coated, fried", 3, 40, amount);
                        valid = true;
                        *//*
                        mealName = "Chicken, broiler, giblets, flour coated, fried";
                        omega3 = 3;
                        omega6 = 40;
                         *//*
                        break;
                    case "Chicken, broiler, giblets, simmered":
                        meal = new Meal("Chicken, broiler, giblets, simmered", 2, 16, amount);
                        valid = true;
                        *//*
                        mealName = "Chicken, broiler, giblets, simmered";
                        omega3 = 10;
                        omega6 = 1;
                         *//*
                        break;
                    default:
                        //if user entered food weight but not the meal, make a reminder toast message
                        Toast.makeText(MealInput2.this,"Please input your meal",Toast.LENGTH_SHORT).show();
                        break;

                }*/}
                //if user entered both meal and weight, pass the data to another activity
                if(valid) {
                    gloClass.setMeals(meal);
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
                //else make a reminder toast message
                else if(amountText.equals(""))
                    Toast.makeText(MealInput2.this,"Please input the weight",Toast.LENGTH_SHORT).show();
                else if(foodAte.equals(""))
                    Toast.makeText(MealInput2.this,"Please input the meal",Toast.LENGTH_SHORT).show();
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