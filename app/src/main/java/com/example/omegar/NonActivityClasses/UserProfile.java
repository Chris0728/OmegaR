package com.example.omegar.NonActivityClasses;

import java.util.Calendar;

public class UserProfile {
    private final double inchesToCm = 2.54;
    private final double lbsToGrams = 454;

    private static int accountID = 0;

    private String firstName;
    private String lastName;
    private Calendar DOB;
    private Calendar currentDate;
    private int age;
    private double height;			//Measured in cm
    private double weight;			//Measured in kg

    public UserProfile() {
        accountID++;
        currentDate = Calendar.getInstance();
    }

    //Setters
    public void setFirstName(String name) {
        this.firstName = name;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public void setDOB(int day, int month, int year) {
        this.DOB.set(year, month, day);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
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

    public Calendar getDOB() {
        return this.DOB;
    }

    public int getAge() {
        return this.age;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWeight() {
        return this.weight;
    }
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
