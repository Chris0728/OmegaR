package com.example.omegar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Homepage extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private ImageButton burgerMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        dl = (DrawerLayout)findViewById(R.id.homepage);
        t = new ActionBarDrawerToggle(this, dl,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        final Intent registerInfo = getIntent();
        //unloading all data from previous activity
        final String name = registerInfo.getStringExtra("Name");
        final String email = registerInfo.getStringExtra("Email");
        final String phone = registerInfo.getStringExtra("Phone");
        final String pwd = registerInfo.getStringExtra("Pwd");


        dl.addDrawerListener(t);
        t.syncState();

        burgerMenu = findViewById(R.id.burgermenu);
        nv = (NavigationView)findViewById(R.id.navigation);
        burgerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dl.openDrawer(nv);
            }
        });
        //ADD the line "drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);" if want to remove the default sliding function
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                //Fragment page = null;
                //Class fragmentClass = null;

                switch(id)
                {
                    case R.id.nav_mealHistory:
                        Toast.makeText(Homepage.this, "meal History",Toast.LENGTH_SHORT).show();
                        Intent intentHistory = new Intent(getBaseContext(), mealHistory.class);
                        startActivity(intentHistory);
                        //fragmentClass = MealHistory.class;
                        break;
                    case R.id.nav_Education:
                        Toast.makeText(Homepage.this, "Education",Toast.LENGTH_SHORT).show();
                        Intent intentEducation = new Intent(getBaseContext(), Education.class);
                        startActivity(intentEducation);
                        break;
                    case R.id.nav_TermsAndConditions:
                        Toast.makeText(Homepage.this, "Terms And Conditions",Toast.LENGTH_SHORT).show();
                        Intent intentTerms = new Intent(getBaseContext(), TermsAndConditions.class);
                        startActivity(intentTerms);
                        break;
                    case R.id.nav_logout:
                        Toast.makeText(Homepage.this, "Logout",Toast.LENGTH_SHORT).show();
                        dl.closeDrawer(nv);
                        break;
                    default:
                        return true;
                }

                //this was used for fragments. Ignore
                try {
                    //page = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                //FragmentManager fragmentManager = getSupportFragmentManager();
                //fragmentManager.beginTransaction().replace(R.id.flContent, page).commit();
                dl.closeDrawer(nv);
                return true;

            }
        });
        final ImageButton profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(getBaseContext(), Profile.class);

                intentProfile.putExtra("Name", name);
                intentProfile.putExtra("Email", email);
                intentProfile.putExtra("Phone", phone);
                intentProfile.putExtra("Pwd", pwd);

                startActivity(intentProfile);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}

