package com.example.austinsehnert.smartart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.concurrent.TimeUnit;
import okhttp3.WebSocket;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocketListener;
import okio.ByteString;


public class MainActivity extends AppCompatActivity {

    public static WebSocket ws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //Intent signIn = new Intent(this, SignInActivity.class);
       //startActivity(signIn);
        /*Intent global = new Intent(this, GlobalActivity.class);
        startActivity(global);*/


       new WebSocketEcho().run();

    }

    class WebSocketEcho extends WebSocketListener {

        private void run() {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(0, TimeUnit.MILLISECONDS)
                    .build();

            Request request = new Request.Builder()
                    .url("ws://echo.websocket.org")
                    .build();
            client.newWebSocket(request, this);

            // Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
            client.dispatcher().executorService().shutdown();
        }

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            webSocket.send("Hellp...");
            webSocket.send("...World!");
            webSocket.send(ByteString.decodeHex("deadbeef"));
            webSocket.close(1000, "Goodbye, World!");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            System.out.println("MESSAGE: " + text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            System.out.println("MESSAGE: " + bytes.hex());
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(1000, null);
            System.out.println("CLOSE: " + code + " " + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            t.printStackTrace();
        }

    }
}













