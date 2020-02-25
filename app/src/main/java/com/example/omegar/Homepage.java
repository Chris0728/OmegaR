package com.example.omegar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omegar.NonActivityClasses.GlobalClass;
import com.example.omegar.NonActivityClasses.MealData;
import com.google.android.material.navigation.NavigationView;

public class Homepage extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private ImageButton burgerMenu;
    private TextView ratioDisplay;
    public static MealData meals = new MealData();
    private ImageView redCircle, yellowCircle, greenCircle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        final GlobalClass gloClass = (GlobalClass) getApplication();


        ratioDisplay = findViewById(R.id.ratioDisplay);
        redCircle = findViewById(R.id.redCircle);
        yellowCircle = findViewById(R.id.yellowCircle);
        greenCircle = findViewById(R.id.greenCircle);

        if(gloClass.getMeals().getSize()==0)
        {ratioDisplay.setText("0:0");}
        else {
            ratioDisplay.setText(gloClass.getMeals().calculate());
        }
        int range=0;
        if(gloClass.getMeals().geto6()<=4){
            range = 1;
        }
        if(gloClass.getMeals().geto6()>4 && gloClass.getMeals().geto6()<=10){
            range =2;
        }
        if(gloClass.getMeals().geto6()>10){
            range = 3;
        }
        switch(range){
            case 1:
                    greenCircle.setVisibility(View.VISIBLE);
                    yellowCircle.setVisibility(View.INVISIBLE);
                    redCircle.setVisibility(View.INVISIBLE);
                    break;
            case 2:greenCircle.setVisibility(View.INVISIBLE);
                    yellowCircle.setVisibility(View.VISIBLE);
                    redCircle.setVisibility(View.INVISIBLE);
                    break;
            case 3:greenCircle.setVisibility(View.INVISIBLE);
                    yellowCircle.setVisibility(View.INVISIBLE);
                    redCircle.setVisibility(View.VISIBLE);
                    break;
        }
        dl = (DrawerLayout)findViewById(R.id.homepage);
        t = new ActionBarDrawerToggle(this, dl,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //!!!!!!!!!!! Change the next 5 variables to final!
        /*Intent registerInfo = getIntent();
        //unloading all data from previous activity
        String name = registerInfo.getStringExtra("Name");
        String email = registerInfo.getStringExtra("Email");
        String phone = registerInfo.getStringExtra("Phone");
        String pwd = registerInfo.getStringExtra("Pwd");
*/

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

                        AlertDialog.Builder builder = new AlertDialog.Builder(Homepage.this);
                        builder.setTitle("Log Out");
                        builder.setMessage("Are you sure to log out?");
                        // add a button
                        builder.setPositiveButton("Log out", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // reset the data when log out
                                gloClass.reset();
                                Intent intentLogout = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intentLogout);
                            }
                        });
                        builder.setNegativeButton("Cancel", null);
                        // create and show the alert dialog
                        AlertDialog dialog = builder.create();
                        dialog.show();
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
        final ImageButton profile = findViewById(R.id.profileGender);

        //remove next 4 vars
        /*
        final String nameTemp = name;
        final String emailTemp = name;
        final String phoneTemp = name;
        final String pwdTemp = name;
*/
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(getBaseContext(), Profile.class);

                /*intentProfile.putExtra("Name", nameTemp);
                intentProfile.putExtra("Email", emailTemp);
                intentProfile.putExtra("Phone", phoneTemp);
                intentProfile.putExtra("Pwd", pwdTemp);
*/
                startActivity(intentProfile);
            }
        });

        final Button inputMealBtn = findViewById(R.id.input_meal_button);
        inputMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMealInput = new Intent(getBaseContext(), MealInput.class);
                startActivity(intentMealInput);
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed(){

    }

}

