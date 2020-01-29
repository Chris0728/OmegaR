package com.example.omegar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.omegar.NonActivityClasses.GlobalClass;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button login = findViewById(R.id.loginbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //method below loads userProfile from DB, provided that the email and account exists.
                //Also sets gloClass private vals to DB results. Doing this will allow any activity to access
                //this user's user-profile.
                try {
                    //GlobalClass gloClass = new GlobalClass();
                            //GlobalClass gloClass = (GlobalClass) getApplication();
                            //gloClass.getUserByEmail("testEmail@testEmail.com"); //this sets all user's info to match DB's user info.
                    /*
                    Im hardcoding the email rn because
                    there's an error with getting the String representation from
                    textView.
                    */

                    Intent intent = new Intent(getBaseContext(), Homepage.class);
                    //checking if email TxtField matches DB email.
                            //if ("testEmail@testEmail.com".equals(gloClass.getEmail()/*((GlobalClass) new GlobalClass()).getEmail()*/)) {
                        /*intent.putExtra("id", gloClass.getId());
                        intent.putExtra("email", gloClass.getEmail());
                        intent.putExtra("pass", gloClass.getPass());
                        intent.putExtra("age", gloClass.getAge());*/
                        startActivity(intent);
                            //}


                }catch(Exception e){
                    Log.e("Login & gloClass failed", Log.getStackTraceString(e));
                    Toast.makeText(login.this,e.toString(),Toast.LENGTH_LONG).show();
                }
                /*Intent intent = new Intent(getBaseContext(), Homepage.class);
                startActivity(intent);*/
            }
        });
        TextView signup = findViewById(R.id.signUp);
        //setting the sign up textview to be clickable and go to Register activity
        /*signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Register.class);
                startActivity(intent);
            }
        });

         */
        String SignUp = "Sign Up";
        SpannableString signupspan = new SpannableString(SignUp);
        ClickableSpan LinktoSignUp = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent registerIntent = new Intent(getBaseContext(),Register.class);
                startActivity(registerIntent);
            }
        };
        signupspan.setSpan(LinktoSignUp,0,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signup.setText(signupspan);
        signup.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
