import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

public class MealHistoryRanker {
    private HashMap<String, MealShell> rankMap;
    private ArrayList<MealShell> rankList;


    public MealHistoryRanker(){
        this.rankMap = new HashMap<String, MealShell>();
        this.rankList = new ArrayList<MealShell>();
    }

    //Setters

    //


    //Getters
    public int getMealCount(Meal meal){
        String mealName = meal.getName();

        if(this.rankMap.containsKey(mealName))
            return this.rankMap.get(mealName).getCount();

        return 0;
    }
    //


    //General Methods
    public void addMeal(Meal meal){
        String mealName = meal.getName();
        MealShell tempMeal = null;

        if(this.rankMap.containsKey(mealName)){
            tempMeal = this.rankMap.get(mealName);
            tempMeal.increaseCount(1);
        } else {
            tempMeal = new MealShell(mealName);
            tempMeal.increaseCount(1);
            this.rankMap.put(mealName, tempMeal);
        }
    }

    public void rankMeals(){
        Iterator it = this.rankMap.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry mapElement = (Map.Entry) it.next();
            this.rankList.add(mapElement.getValue());
        }

        Collections.sort(this.rankList);
    }

    public MealShell[] getTopXMeals(int num){
        if(num > this.rankList.size())
            num = this.rankList.size();

        MealShell[] topX = new MealShell[num];
        rankMeals();

        for(int i=0; i<num; i++)
            topX[i] = this.rankList.get(i);

        return topX;
    }




}