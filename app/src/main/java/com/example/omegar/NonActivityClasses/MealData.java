package com.example.omegar.NonActivityClasses;

import java.util.ArrayList;
import java.util.List;

public class MealData {
    List<Meal> meals = new ArrayList<Meal>();
    double saveo6=0;
    double o6 = 0;
    double o3 = 0;
    int simpleRatio = 0;
    //Constructor
    public MealData(){

    }


    //Methods
    public void addMeal(Meal m){
        meals.add(m);
    }

    public String calculate(){

        StringBuilder builder = new StringBuilder();

        for(Meal m : meals){
            o6+=m.getOmega6();
            o3+=m.getOmega3();
        }
        double complexRatio = 0;

        int roundedO6 = (int) Math.round(o6);
        int roundedO3 = (int) Math.round(o3);
        if(roundedO6>roundedO3){
            try {
            complexRatio = o6/o3;
            simpleRatio = (int) Math.round(complexRatio);
            builder.append(simpleRatio + " : 1");
            } catch (ArithmeticException e){
                builder.append(simpleRatio + ": 0");
            }
        } else {
            try{
            complexRatio = o3/o6;
            simpleRatio = (int) Math.round(complexRatio);
                builder.append("1: " + simpleRatio);
            } catch (ArithmeticException e){
                builder.append("0 : " + simpleRatio);
            }
        }


        return builder.toString();
    }

    public double getOmegaRatio(){
      return o6/o3;
    }
    public int getSize(){return meals.size();}



}
