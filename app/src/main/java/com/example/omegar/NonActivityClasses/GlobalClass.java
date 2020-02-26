package com.example.omegar.NonActivityClasses;

import android.app.Application;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.*; //not needed i think
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

    /*
    OK this is very very very very bad.
    https://developer.android.com/training/data-storage
    This class should never be in the final rollout app.
    */

public class GlobalClass extends Application {

    //variables
    private String id;
    private String name; //Currently, no attribute for name in DB in userProfile relation.
    private String email;
    private String pass;
    private String age;
    private String weight;
    private String disease;
    private String bp;
    private String gender;
    private boolean loadNutrient = false; //if nutrients_amount.json is loaded once, this would be true and no need to load again

    ArrayList<Meal_nutrient> CNF_nutrients = new ArrayList<>();

    DBConnector dbC = new DBConnector();
    private MealData meals;


    //MealData strictly for MealHistory.activity
    private MealData monthlyMeals;
    private MealData weeklyMeals;

    //Constructor
    //public so that login.java can make this obj.
    public GlobalClass() {
        meals = new MealData();
        setId("");
        setEmail("");
        setPass(""); //delete pass?
        setAge("");
        setBp("");
        setDisease("");
        setGender("");
        setWeight("");
    }

    public GlobalClass(String name, String email, String pass, String age) {
        dbC = new DBConnector();
        setName(name);
        setEmail(email);
        //should password be here?
        this.pass = pass;
        setAge(age);
        meals = new MealData();
    }


    //
    //
    //METHODS
    //
    //
    public void reset() {
        meals = new MealData();
        setId("");
        setEmail("");
        setPass(""); //delete pass?
        setAge("");
        setBp("");
        setDisease("");
        setGender("");
        setWeight("");
    }

    public void logonAtRegister(String name, String email) {
        setName(name);
        setEmail(email);
    }

    public void getUserByEmail(String email) {
        String[] temp = (new DBConnector()).dbGetUserByEmail(email);
        if (temp.length == 0) {
            Log.d("Igat Igat", "String array from ResultSet is Empty!");
            return;
        }
        setId(temp[0]);
        setEmail(temp[1]);
        setPass(temp[2]); //delete pass?
        setAge(temp[3]);
        setBp(temp[4]);
        setDisease(temp[5]);
        setGender(temp[6]);
        setWeight(temp[7]);
    }

    /*Getter and Setter methods*/
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getAge() {
        return age;
    }

    public String getWeight() {
        return weight;
    }

    public String getDisease() {
        return disease;
    }

    public String getBp() {
        return bp;
    }

    public String getGender() {
        return gender;
    }

    public boolean isLoadNutrient() {
        return loadNutrient;
    }

    public MealData getMeals() {
        return this.meals;
    }

    public MealData getMonthlyMeals() {
        return monthlyMeals;
    }

    public ArrayList<Meal_nutrient> getCNF_nutrients() {
        return CNF_nutrients;
    }

    public void setLoadNutrient(boolean f) {
        loadNutrient = f;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMeals(Meal meal) {
        meals.addMeal(meal);
    }

    /*End of Getter and Setter methods*/
    public Boolean emailExists(String email) throws SQLException {
        return dbC.emailExists(email);
    }

    public void addNutrients(Meal_nutrient nutrient) {
        CNF_nutrients.add(nutrient);
    }

    public Boolean pwMatch(String email, String password) throws Exception {
        return dbC.pwMatch(email, password);
    }

    public void signUserUp(String name, String email, String pwd) throws SQLException {
        dbC.signUserUp(name, email, pwd);
    }

    // TODO: implement below
    public void pushMealToDB(Meal m) throws SQLException{
        Calendar cal = Calendar.getInstance();
        String mealDate = convertDate(cal);


        //dbC.pushMeal(getId(), mealDate, meals.getMeals().get(meals.getSize()-1).getName(), meals.getMeals().get(meals.getSize()-1).getOmega3(), meals.getMeals().get(meals.getSize()-1).getOmega6(),  meals.getMeals().get(meals.getSize()-1).getAmount());
        dbC.pushMeal(getId(), mealDate, m.getName(), m.getOmega3(), m.getOmega6(),  m.getAmount());

    }

    public String getTodayFullDate() {
        Calendar cal = Calendar.getInstance();
        return convertDate(cal);
    }

    //gets the
    public String getTodayDate() {
        String s = getTodayFullDate();
        String[] bits = s.split("-");
        return bits[bits.length - 1];
    }


    //This method sets monthlyMeals to DB's
    public void loadMontlyMeals() {
        //gonna hardcode the start and end date for peer testing purposes.
        Calendar cal1 = generateCalendar(2020, 02, 1);
        Calendar cal2 = generateCalendar(2020, 03, 1);
        String startDate = convertDate(cal1);
        String endDate = convertDate(cal2);
        try {
            monthlyMeals = getMealsFromDB(getId(), startDate, endDate);
        } catch (SQLException e) {
            //System.exit(0);
            monthlyMeals = null; //invalidate monthlyMeals
        }
    }

    public MealData getMealsFromDB(String uid, String startDate, String endDate) throws SQLException {
        ResultSet rst = dbC.getMealsFromDB(uid, startDate, endDate);
        MealData userMeals = new MealData();
        while (rst.next()) {

            //below is Igat's way of forcing only date(23 because its feb 23rd) to be in calendar object for the purpose of adding a meal
            //because Robin's a turd. jk. but seriously tho, bcuz u dint fully implement calendar, im getting fucked now for peer testing
            //by working until 3am.
            int date = rst.getDate("mealDate").getDate();
            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.set(0, 0, date);

            userMeals.addMeal(new Meal(rst.getString("name"),
                    rst.getDouble("omega3"),
                    rst.getDouble("omega6"),
                    rst.getDouble("amount"),
                    cal));
        }
        return userMeals;
    }


    //TODO:
    //This method returns an arrayList that holds all meals eaten by this user at this date
    public List<Meal> getMealsAtDate(int date){
        List<Meal> tempMeals = new ArrayList<Meal>();

        //iterate through all meals loaded from DB this month.
        //NOTE: monthlyMeals.meals is different from GlobalClass.meals,
        //      monthlyMeals.meals is generated from loadMonthlyMeals
        for (Meal m: monthlyMeals.meals) {
            if(m.getMealDate() == date){
                tempMeals.add(m);
            }
        }



        return tempMeals;
    }


    //This method will calculate the total O3 from all meals eaten this date.
    public int calculateRoundedTotalOmega3ofMealsAtDate(int date){
        List<Meal> tempMeals = getMealsAtDate(date);
        double total = 0;

        for (Meal m: tempMeals) {
            total += m.getOmega3();
        }

        return (int) Math.round(total);
    }


    //This method is purely for the sake of the mealHistory.
    //Never to be used for inserting into DB
    private Calendar generateCalendar(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal;
    }

    //Convert's calendar object to String representation.
    public String convertDate(Calendar cal) {
        int[] timestamp = new int[6];

        timestamp[0] = cal.get(Calendar.YEAR);
        timestamp[1] = cal.get(Calendar.MONTH);
        timestamp[2] = cal.get(Calendar.DATE);
        timestamp[3] = cal.get(Calendar.HOUR);
        timestamp[4] = cal.get(Calendar.MINUTE);
        timestamp[5] = cal.get(Calendar.SECOND);

        StringBuilder sb = new StringBuilder();

        sb.append(timestamp[0]);
        sb.append("-");
        sb.append(timestamp[1]);
        sb.append("-");
        sb.append(timestamp[2]);
        sb.append(" ");

        sb.append(timestamp[3]);
        sb.append(":");
        sb.append(timestamp[4]);
        sb.append(":");
        sb.append(timestamp[5]);

        return sb.toString();
    }

}
