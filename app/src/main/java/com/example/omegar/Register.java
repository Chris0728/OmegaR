package com.example.omegar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Homepage.class);
                EditText name = findViewById(R.id.registerName);
                EditText email = findViewById(R.id.registerEmail);
                EditText Phone = findViewById(R.id.registerPhone);
                EditText Pwd = findViewById(R.id.registerPwd);
                intent.putExtra("Name",name.getText().toString());
                intent.putExtra("Email",email.getText().toString());
                intent.putExtra("Phone",Phone.getText().toString());
                intent.putExtra("Pwd",Pwd.getText().toString());
                startActivity(intent);
            }

        });
    }
}
