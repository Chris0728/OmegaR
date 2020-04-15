package com.example.omegar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omegar.NonActivityClasses.GlobalClass;

import java.sql.SQLException;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = findViewById(R.id.register);
        final EditText nameField = findViewById(R.id.registerName);
        final EditText emailField = findViewById(R.id.registerEmail);
        final EditText pwdField = findViewById(R.id.registerPwd);
        final EditText pwdConfField = findViewById(R.id.registerPwdConfirm);

        final CheckBox terms = findViewById((R.id.checkedTextView));
        String Terms = "I accept the terms and conditions";
        SpannableString termsspan = new SpannableString(Terms);
        ClickableSpan LinktoTerms = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent TermsIntent = new Intent(getBaseContext(),TermsAndConditions.class);
                startActivity(TermsIntent);
            }
        };
        termsspan.setSpan(LinktoTerms,13,33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        terms.setText(termsspan);
        terms.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean allValid = true;
                boolean[] validFields = new boolean[5];
                    /*
                        [0]: name
                        [1]: email
                        [2]: pwd
                        [3]: pwdConf
                        [4]: terms
                     */

                String[] errorMsgs = new String[5];
                errorMsgs[0] = "Name Field Empty\n";
                errorMsgs[1] = "Email Field Empty\n";
                errorMsgs[2] = "Password Field Empty\n";
                errorMsgs[3] = "Passwords Do Not Match\n";
                errorMsgs[4] = "Please Agree to Terms and Conditions";

                String name = nameField.getText().toString().trim();
                String email = emailField.getText().toString().trim();
                String pwd = pwdField.getText().toString().trim();
                String pwdConf = pwdConfField.getText().toString().trim();


                //Name
                if(name.isEmpty())
                    validFields[0] = false;
                else
                    validFields[0] = true;

                //Email
                if(email.isEmpty())
                    validFields[1] = false;
                else
                    validFields[1] = true;

                //Pwd
                if(pwd.isEmpty())
                    validFields[2] = false;
                else
                    validFields[2] = true;

                //PwdConf
                if(pwdConf.isEmpty() || !pwdConf.equals(pwd))
                    validFields[3] = false;
                else
                    validFields[3] = true;

                //Terms Box
                if(!terms.isChecked())
                    validFields[4] = false;
                else
                    validFields[4] = true;

                StringBuilder sb = new StringBuilder("Error:\n");
                for(int i=0; i<validFields.length; i++){
                    if(validFields[i] == false) {
                        allValid = false;
                        sb.append(errorMsgs[i]);
                    }
                }

                if(allValid == false){
                    Toast.makeText(Register.this, sb.toString(), Toast.LENGTH_LONG).show();
                    return;
                }


                final GlobalClass gloClass = (GlobalClass) getApplication();
                try {
                    //if-statement below checks if email is already registered on DB.
                    if (gloClass.emailExists(email)) {
                        Toast.makeText(Register.this, "Email is already registered. Try different email!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    //register user on DB.
                    gloClass.signUserUp(name, email, pwd);

                    //save user info locally on the APP inside gloClass.
                    gloClass.logonAtRegister(name, email);


                } catch(SQLException e){
                    Toast.makeText(Register.this, e.toString(), Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Toast.makeText(Register.this, e.toString(), Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(getBaseContext(), login.class);
                startActivity(intent);
            }

        });
    }
}
