package com.example.omegar.NonActivityClasses;

import java.util.ArrayList;
import java.util.List;

public class MealData {
    List<Meal> meals = new ArrayList<Meal>();
    public MealData(){

    }
    public void addMeal(Meal m){
        meals.add(m);
    }

    public String calculate(){

        StringBuilder builder = new StringBuilder();
        double o6 = 0;
        double o3 = 0;
        for(Meal m : meals){
            o6+=m.getOmega6();
            o3+=m.getOmega3();
        }

        o6 = o6/o3;
        builder.append(o6 + ":"+ 1);

        return builder.toString();
    }

    public int getSize(){
        return meals.size();
    }


}
