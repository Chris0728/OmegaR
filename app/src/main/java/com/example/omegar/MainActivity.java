package com.example.omegar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omegar.NonActivityClasses.DBConnector;

import com.example.omegar.NonActivityClasses.foodArray;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        DBConnector m = new DBConnector();
        String sql = m.connectThenSelect();


        TextView re = findViewById(R.id.welcomeText);
        re.setText(sql);

        Toast.makeText(MainActivity.this,sql,Toast.LENGTH_LONG).show();
        re.setText(sql);
        Button starter = findViewById(R.id.button);

        starter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), login.class);
                startActivity(intent);
            }
        });
    }

}
