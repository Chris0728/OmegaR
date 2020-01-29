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

import org.w3c.dom.Text;

public class login extends AppCompatActivity {
    Button login;
    TextView txtEmail;
    TextView txtPass;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login = findViewById(R.id.loginbtn);
        txtEmail = findViewById(R.id.userEmail);
        txtPass = findViewById(R.id.userPassword);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalClass gloClass = (GlobalClass) getApplication();

                    String emailCheckWithDB = txtEmail.getText().toString().trim(); //trim removes whitespaces
                    String passCheckWithDB = txtPass.getText().toString().trim(); //whitespaces not allowed in password

                    try {
                        if ("".equals(emailCheckWithDB) || "".equals(passCheckWithDB)) {
                            Toast.makeText(login.this, "Invalid email or empty password.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        if (!gloClass.emailExists(emailCheckWithDB)) {
                            Toast.makeText(login.this, "Cannot find email!", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (!gloClass.pwMatch(emailCheckWithDB, passCheckWithDB)) {
                            Toast.makeText(login.this, "Incorrect password.", Toast.LENGTH_LONG).show();
                            return;
                        }
                    /*if (gloClass.emailExists(emailCheckWithDB)) {
                        Toast.makeText(login.this, "Cannot find email!", Toast.LENGTH_LONG).show();
                        return;
                    }*/
                    } catch (Exception e) {
                        Toast.makeText(login.this, e.toString(), Toast.LENGTH_LONG).show();
                    }

                Intent intent = new Intent(getBaseContext(), Homepage.class);
                try {
                    gloClass.getUserByEmail("testEmail@testEmail.com"); //this sets all user's info to match DB's user info.

                    
                    /*
                    Im hardcoding the email rn because
                    there's an error with getting the String representation from
                    textView.
                    */

                    intent = new Intent(getBaseContext(), Homepage.class);
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

                startActivity(intent);// why needed here?
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
