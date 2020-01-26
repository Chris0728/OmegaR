package com.example.omegar.NonActivityClasses;
/*
//delete import statements?
import android.app.Activity;
import android.content.Context;
import android.os.DropBoxManager;
import android.util.Log;
import android.widget.Toast;
*/

import androidx.appcompat.app.AppCompatActivity;

import java.sql.*;



public class DBConnector {
    String url = "";
    String user = "?";
    String pw = "?";

    public DBConnector(/*String url, String user, String pw*/){
        /*try {
            Connection con = DriverManager.getConnection(url,user,pw);
        }catch (SQLException e){
            //Toast.makeText(activity.this, "DB Connection error!",Toast.LENGTH_SHORT).show();
            //(1) I want to make a toast msg in the case where exception is thrown.
            //(2) The problem is getting the correct class name to pass as context in 1st param.
        }*/

    }

     public  String connectThenSelect(/*String attributes, String table*/){
        DBConnector myDB = new DBConnector();
        String s= "";
        try{
            Connection con = DriverManager.getConnection(url,user,pw);

            s = s + "Connection sucessful.\n";
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(myDB.select("*","dept"));


            s += ">>>Printing result set.\n";
            s+="+-------------------------------------+\n";
            s+="| dno   |   dname         |    age    |\n";
            s+="|-------------------------------------|\n";
            while(rst.next()) {
               s+= (rst.getString("dno")) + "     " + rst.getString("dname") + "  "  + rst.getString("age") + "\n";
                //System.out.format("| %2s | %15s | %2s|\n", rst.getString("dno"), rst.getString("dname"), rst.getString("age"));
            }
            s+="+-------------------------------------+\n";
            s+="/n>>>Printing done.\n";
            con.close();

        }catch(SQLException e){
            e.printStackTrace();

        }
        return s;
    }

    public String test(){
        String aa="";
        try {
            Connection con = DriverManager.getConnection(url,user,pw);
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM dept WHERE dno='1';");

            rst.next();
            aa = rst.getString("dno") + "        " + rst.getString("dname") + "      " + rst.getString("age");

        }catch(SQLException e){
            aa= "Error: " + e + "||";
        }

        return aa;
    }

    String select(String attributes, String table){
        return "SELECT " + attributes + " FROM " + table + ";";
    }

    String insert(String relation, String values) {
        return "INSERT INTO " + relation + " VALUES " + values + ";";
    }



}
