package com.example.omegar.NonActivityClasses;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.JsonReader;
import android.util.Log;

import com.example.omegar.R;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import android.content.res.Resources;

public class foodArray {
    public ArrayList<food> array;
    private Context context;

    // TODO: 2020/1/23 THIS CLASS IS IN PROGRESS, UNUSEABLE, AND WILL BE COMPLETED NEXT PROGRAMMING SESSION - IGAT/CHRIS
    public foodArray(Context context) {
        this.context = context;
    }

    public foodArray() {

    }
}
