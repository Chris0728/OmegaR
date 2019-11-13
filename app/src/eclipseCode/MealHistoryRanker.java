import java.util.ArrayList;

public class MealHistoryRanker {
    private ArrayList<String> nameTable;
    private ArrayList<Integer> rankTable;

    public MealHistoryRanker(){
        this.strTable = new ArrayList<String>();
        this.rankTable = new ArrayList<Integer>();
    }

    public void addMeal(Meal meal){
        String name = meal.getName();
        int index, tempNum;

        if(this.nameTable.contains(name)){
            index = this.nameTable.indexOf(name);
            tempNum = this.rankTable.get(index);
            tempNum++;
            this.rankTable.set(index, tempNum);
        } else {
            this.nameTable.add(name);
            index = this.nameTable.indexOf(name);
            tempNum = 1;
            this.rankTable.ensureCapacity(index);
            this.rankTable.set(index, tempNum);
        }
    }

    public String[] getTop5Meals(){
        int num = 5;

        String[] maxHeap;
        int[] values;
        int[] indexes;

        if(num > this.nameTable.size())
            num = this.nameTable.size();

        maxHeap = new String[num];
        indexes = new int[num];

        for(int i=0; i<num; i++){
            values[i] = this.rankTable.get(i);
            indexes[i] = i;
        }

        return maxHeap
    }




}