package com.example.omegar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omegar.NonActivityClasses.DBConnector;

import com.example.omegar.NonActivityClasses.GlobalClass;
import com.example.omegar.NonActivityClasses.Meal_nutrient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GlobalClass gloClass = (GlobalClass) getApplication();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toast.makeText(MainActivity.this, "Online services loaded.", Toast.LENGTH_LONG).show();

        TextView welcomeTextField = findViewById(R.id.welcomeText);
        welcomeTextField.setVisibility(View.GONE);


        Button starter = findViewById(R.id.button);
        if(!gloClass.isLoadNutrient()) {
            try {
                AssetManager assetManager = MainActivity.this.getAssets();
                com.google.gson.stream.JsonReader jsonReader = new com.google.gson.stream.JsonReader(new InputStreamReader(assetManager.open("nutrient_database/Simplified_nutrient_amount_api.json")));

                Gson gson = new GsonBuilder().create();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    Meal_nutrient nutrient = gson.fromJson(jsonReader, Meal_nutrient.class);
                    gloClass.addNutrients(nutrient);
                }
                jsonReader.close();
            } catch (UnsupportedEncodingException e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            } catch (
                    FileNotFoundException e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
            gloClass.setLoadNutrient(true);
        }

        starter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), login.class);
                startActivity(intent);
            }
        });
    }

}
