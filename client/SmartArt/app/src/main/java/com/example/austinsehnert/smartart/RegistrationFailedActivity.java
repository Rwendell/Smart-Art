package com.example.austinsehnert.smartart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegistrationFailedActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.example.austinsehnert.smartart.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_failed);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(NewUserRegActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.failure_message);
        textView.setText(message);
    }

    public void returnToUserRegistration(View view){
        Intent backToUserRegistration = new Intent(this, NewUserRegActivity.class);
        startActivity(backToUserRegistration);
    }
}
