package com.example.omegar.NonActivityClasses;

import android.app.Application;
import android.util.Log;

import java.sql.*; //not needed i think

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

    DBConnector dbC;

    //Constructor
    //public so that login.java can make this obj.
    public GlobalClass() { dbC = new DBConnector();
    }

    public GlobalClass(String name, String email, String pass, String age) {
        dbC = new DBConnector();
        setName(name);
        setEmail(email);
        //should password be here?
        this.pass = pass;
        setAge(age);
    }


    //
    //
    //METHODS
    //
    //
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
    /*End of Getter and Setter methods*/
    public Boolean emailExists(String email) throws SQLException{
        return dbC.emailExists(email);
    }

    public Boolean pwMatch(String email, String password) throws Exception{
        return dbC.pwMatch(email, password);
    }


}
