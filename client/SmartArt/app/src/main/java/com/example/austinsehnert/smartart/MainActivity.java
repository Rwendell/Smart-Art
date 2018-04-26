package com.example.austinsehnert.smartart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       //Intent signIn = new Intent(this, SignInActivity.class);
       //startActivity(signIn);

       Intent select = new Intent(this, SelectBoardActivity.class);
       startActivity(select);

        //Intent g = new Intent(this, GlobalActivity.class);
        //startActivity(g);

        Intent socket = new Intent(this, SocketService.class);
        startService(socket);

    }
}












