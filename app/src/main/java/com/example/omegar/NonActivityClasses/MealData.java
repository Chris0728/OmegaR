package com.example.omegar.NonActivityClasses;

import java.util.ArrayList;
import java.util.List;

public class MealData {
    List<Meal> meals = new ArrayList<Meal>();
    double saveo6=0;
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
        saveo6 = o6;
        builder.append(Math.round(o6) + " : "+ 1);

        return builder.toString();
    }

    public double geto6(){
      return saveo6;
    }
    public int getSize(){
        return meals.size();
    }


}
