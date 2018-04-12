package com.example.austinsehnert.smartart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 *
 */
public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


    }

    public void proceedToGlobal(View view) {
        Intent toGlobal = new Intent(this, GlobalActivity.class);
        startActivity(toGlobal);
    }
}
