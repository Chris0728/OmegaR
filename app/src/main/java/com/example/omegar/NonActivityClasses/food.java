package com.example.omegar.NonActivityClasses;

import com.google.gson.annotations.SerializedName;

public class food {
    @SerializedName("food_code")
    public String food_code;
    @SerializedName("food_description")
    public String food_description;

    public food(String code, String des){
        this.food_code = code;
        this.food_description = des;
    }

    public String getFood_code() {
        return food_code;
    }
    public String getFood_description(){
        return food_description;
    }
}
