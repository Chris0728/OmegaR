package com.example.omegar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.omegar.ui.drawer;
import com.google.android.material.navigation.NavigationView;

public class homepage extends AppCompatActivity {
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
                switch(id)
                {
                    case R.id.nav_mealHistory:
                        Toast.makeText(homepage.this, "meal History",Toast.LENGTH_SHORT).show();
                        dl.closeDrawer(nv);
                        break;
                    case R.id.nav_Education:
                        Toast.makeText(homepage.this, "Education",Toast.LENGTH_SHORT).show();
                        dl.closeDrawer(nv);
                        break;
                    case R.id.nav_TermsAndConditions:
                        Toast.makeText(homepage.this, "Terms And Conditions",Toast.LENGTH_SHORT).show();
                        dl.closeDrawer(nv);
                        break;
                    case R.id.nav_logout:
                        Toast.makeText(homepage.this, "Logout",Toast.LENGTH_SHORT).show();
                        dl.closeDrawer(nv);
                        break;
                    default:
                        return true;
                }


                return true;

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

