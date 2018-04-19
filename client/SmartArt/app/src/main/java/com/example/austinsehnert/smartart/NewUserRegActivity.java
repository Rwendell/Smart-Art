package com.example.austinsehnert.smartart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * This class allows for new users to register, making a new username/password
 */
public class NewUserRegActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.example.austinsehnert.smartart.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_reg);
    }

    /**
     * Takes in user info and allows users to make a new password. If new password and its
     * confirmation do not add up, the user is prompted to enter information in again
     * @param view the view in which the actvity is displayed in
     */
    public void collectUserInfo(View view) {
        Intent registrationSuccessful = new Intent(this, DisplayMessageActivity.class);
        Intent registrationFailed = new Intent(this, RegistrationFailedActivity.class);

        EditText name = findViewById(R.id.username);
        String message = name.getText().toString();
        String fail = "";



        //usernames.add(message);

        EditText password = findViewById(R.id.password);
        String passwordstr = password.getText().toString();


        EditText password2 = findViewById(R.id.password2);
        String password2str = password2.getText().toString();

        if (!passwordstr.equals(password2str)) {
            fail = "Passwords do not match";
            registrationFailed.putExtra(EXTRA_MESSAGE, fail);
            startActivity(registrationFailed);
            return;
        } else {
            message = "Welcome " + message + "!";
            //passwords.add(passwordstr);

        }

        registrationSuccessful.putExtra(EXTRA_MESSAGE, message);


        startActivity(registrationSuccessful);

    }
}
