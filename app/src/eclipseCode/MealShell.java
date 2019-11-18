import java.util.ArrayList;
import java.util.HashMap;

public class MealShell implements Comparable<MealShell> {
    private String name;
    private int count;

    public MealShell(String name){
        this.name = name;
        this.count = 0;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }

    public void setCount(int count){
        this.count = count;
    }
    //


    //Getters
    public String getName(){
        return this.name;
    }

    public int getCount(){
        return this.count;
    }
    //


    //General Methods
    public void increaseCount(int num){
        this.count += num;
    }

    public int compareTo(MealShell otherMeal){
        if(this.count < otherMeal.getCount())
            return -1;
        else if(this.count > otherMeal.getCount())
            return 1;

        return 0;
    }
    //


}