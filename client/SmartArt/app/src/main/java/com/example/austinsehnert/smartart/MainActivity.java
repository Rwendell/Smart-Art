package com.example.austinsehnert.smartart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.austinsehnert.smartart.utils.ArrayCopy;
import com.example.austinsehnert.smartart.utils.ImgUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.WebSocket;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocketListener;
import okio.ByteString;


public class MainActivity extends AppCompatActivity {

    public static WebSocket ws;
    public  static OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //Intent signIn = new Intent(this, SignInActivity.class);
       //startActivity(signIn);
       Intent global = new Intent(this, GlobalActivity.class);
        startActivity(global);


       new WebSocketEcho().run();

       /*Intent i = new Intent(this, WebSocket.class);
       startService(i);*/

    }

    class WebSocketEcho extends WebSocketListener {

        private void run() {
            client = new OkHttpClient.Builder()
                    .readTimeout(0, TimeUnit.MILLISECONDS)
                    .build();

            Request request = new Request.Builder()
                    .url("ws://proj-309-sb-2.cs.iastate.edu:8080/board/mainBoard")
                    .build();

            ws=client.newWebSocket(request, this);

            // Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
            client.dispatcher().executorService().shutdown();
        }

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
           // webSocket.send("Hellp...");
           // webSocket.send("...World!");
          //  webSocket.send(ByteString.decodeHex("deadbeef"));
          //  webSocket.close(1000, "Goodbye, World!");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            System.out.println("MESSAGE: " + text);

            
            Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);

            if(text.contains("image:")){
                text = text.substring(5);
                String[] fcArr = text.split("\\s+");

                int[] fiBytesAsInt = new int[fcArr.length];
                for (int i = 0; i < text.length(); i++) {
                fiBytesAsInt[i] = Integer.parseInt(fcArr[i]);
                }
                byte[] fiBytes = ArrayCopy.int2byte(fiBytesAsInt);


                //research android filesystem
                //ImgUtils.byteArrtoFile(fiBytes, "here");
            }


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













