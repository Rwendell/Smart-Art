package me.paullicata.socketexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import okhttp3.Response;
import okhttp3.WebSocket;

public class MainActivity extends AppCompatActivity {


    WebSocket w;
    WebSocketEcho e;
    Response r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*e.onOpen(w,r);
        Log.d("Socket", r.toString());*/

    }





}
