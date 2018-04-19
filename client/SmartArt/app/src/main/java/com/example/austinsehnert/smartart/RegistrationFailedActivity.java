package com.example.austinsehnert.smartart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * This activity brings the user back to the registration screen when called
 */
public class RegistrationFailedActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.example.austinsehnert.smartart.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_failed);
    }


    /**
     * Returns user to NewUserRegActivity when called
     * @param view the specific view on screen
     */
    public void returnToSignIn(View view) {
        Intent backToUserRegistration = new Intent(this, NewUserRegActivity.class);
        startActivity(backToUserRegistration);
    }

}
