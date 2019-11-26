package com.example.omegar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button backbtn = findViewById(R.id.back);

        Intent profileIntent = getIntent();
        String name = profileIntent.getStringExtra("Name");
        String email = profileIntent.getStringExtra("Email");
        String phone = profileIntent.getStringExtra("Phone");
        String pwd = profileIntent.getStringExtra("Pwd");

        TextView userName = findViewById(R.id.profileName);
        TextView userEmail = findViewById(R.id.profileEmail);
        TextView userPhone = findViewById(R.id.profilePhone);

        userName.setText(name);
        userEmail.setText(email);
        userPhone.setText(phone);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile.super.onBackPressed();
            }
        });
    }
}
