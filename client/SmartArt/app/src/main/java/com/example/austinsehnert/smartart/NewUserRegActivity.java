package com.example.austinsehnert.smartart;

import com.example.austinsehnert.smartart.R;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class NewUserRegActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.example.austinsehnert.smartart.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_reg);
    }

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
