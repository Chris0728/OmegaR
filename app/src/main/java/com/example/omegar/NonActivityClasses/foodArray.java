package com.example.omegar.NonActivityClasses;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class foodArray {
    public ArrayList<food> array;
    private Context context;
    public foodArray(Context context){
        this.context = context;
    }

    public foodArray(){
    AssetManager am = this.context.getAssets();
    try {
        InputStream input = am.open("nutrient_database/food_api");
        int size = input.available();
        byte[] buffer = new byte[size];
        input.read(buffer);
        input.close();

        // byte buffer into a string
        String foodtext = new String(buffer);
        array.add(new Gson().fromJson(foodtext,food.class));
    }
    catch(IOException e)
    {
        e.printStackTrace();
    }}
}
