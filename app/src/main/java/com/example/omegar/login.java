package com.example.omegar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.loginbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Homepage.class);
                startActivity(intent);
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
