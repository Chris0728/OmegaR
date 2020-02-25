package com.example.omegar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.omegar.NonActivityClasses.GlobalClass;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button backbtn = findViewById(R.id.back);

        /*Intent profileIntent = getIntent();
        String name = profileIntent.getStringExtra("Name");
        String email = profileIntent.getStringExtra("Email");
        String phone = profileIntent.getStringExtra("Phone");
        String pwd = profileIntent.getStringExtra("Pwd");
*/
        TextView userName = findViewById(R.id.profileName);
        TextView userEmail = findViewById(R.id.profileEmail);//using profilePhone textView to display password for now.
        TextView userAge = findViewById(R.id.profileAge);
        TextView userWeight = findViewById(R.id.profileWeight);
        TextView userDisease = findViewById(R.id.profileDisease);
        TextView userGender = findViewById(R.id.profileGender);

        GlobalClass gloClass = (GlobalClass) getApplication();

        userName.setText(gloClass.getId());
        userEmail.setText(gloClass.getEmail());
        userAge.setText(gloClass.getAge());
        userWeight.setText(gloClass.getWeight());
        userDisease.setText(gloClass.getDisease());
        userGender.setText(gloClass.getGender());


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile.super.onBackPressed();
            }
        });
    }
}
