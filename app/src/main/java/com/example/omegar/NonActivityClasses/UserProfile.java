package com.example.omegar.NonActivityClasses;

import java.util.ArrayList;
import java.util.Calendar;

public class UserProfile {
    private final double inchesToCm = 2.54;
    private final double lbsToGrams = 454;

    private static int accountID = 0;

    private String firstName;
    private String lastName;
    private String email;
    private Calendar DOB;
    private Calendar currentDate;
    private int age;
    private String gender;
    private double height;			//Measured in cm
    private double weight;			//Measured in kg
    private int systolicBP;
    private int diastolicBP;
    private ArrayList<String> disease;

    public UserProfile() {
        accountID++;
        currentDate = Calendar.getInstance();
        disease = new ArrayList<String>();
    }

    //Setters
    public void setFirstName(String name) {
        this.firstName = name;
    }
    public void setLastName(String name) {
        this.lastName = name;
    }
    public void setEmail(String email){this.email = email;}
    public void setDOB(int day, int month, int year) {
        this.DOB.set(year, month, day);
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGender(String gender) {this.gender = gender;}
    public void setHeight(double height) {
        this.height = height;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setSysBP(int bp) { this.systolicBP = bp;}
    public void setDiasBP(int bp) {this.diastolicBP = bp;}
    //


    //Getters
    public int getAccountID() {
        return accountID;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getEmail() {return this.email;}
    public Calendar getDOB() {
        return this.DOB;
    }
    public int getAge() {
        return this.age;
    }
    public String getGender() { return this.gender; }
    public double getHeight() {
        return this.height;
    }
    public double getWeight() {
        return this.weight;
    }
    public int getSysBP() { return this.systolicBP;}
    public int getDiasBP(){ return this.diastolicBP;}
    public ArrayList<String> getDisease() {return this.disease;}
    public String getDiseaseAsStr() { return this.disease.toString();}
    //


    //General Methods

    //Converts height from imperial to metric
    public double convertHeightToMetric(double feet, double inches) {
        double heightCm;

        inches += feet*12;
        heightCm = inches*this.inchesToCm;

        return heightCm;
    }

    //Converts weight from imperial to metric
    public double convertWeightToMetric(double weightLbs) {
        double weightKg = weightLbs*this.lbsToGrams;
        return weightKg;
    }

    //Gets age based on DOB
    public int calculateAge() {
        int year = this.DOB.get(Calendar.YEAR);
        int month = this.DOB.get(Calendar.MONTH);
        int day = this.DOB.get(Calendar.DATE);
        int age;

        this.currentDate.add(Calendar.YEAR, year*-1);
        this.currentDate.add(Calendar.MONTH, month*-1);
        this.currentDate.add(Calendar.DATE, day*-1);

        age = currentDate.get(Calendar.YEAR);
        this.currentDate.getInstance();

        return age;
    }
}
