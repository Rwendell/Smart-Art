package com.example.austinsehnert.smartart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.austinsehnert.smartart.utils.ArrayCopy;
import com.example.austinsehnert.smartart.utils.ImgUtils;
import com.google.gson.Gson;

import java.io.FileOutputStream;
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
        
       /*Intent signIn = new Intent(this, SignInActivity.class);
       startActivity(signIn);*/

       //Intent select = new Intent(this, SelectBoardActivity.class);
       //startActivity(select);

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

            //Log.d("test", text);

            if(text.contains("image:")){
                text = text.substring(7);

                String fcArr[] = text.split("\\s+");

                //Log.d("text","" + fcArr.length + " " + fcArr[fcArr.length] + "");


                int[] fiBytesAsInt = new int[fcArr.length];




                for (int i = 0; i < fcArr.length; i++) {
                fiBytesAsInt[i] = Integer.parseInt(fcArr[i]);
                }
//                Log.d("fiBytes","" + fiBytesAsInt[fiBytesAsInt.length]);

                byte[] fiBytes = ArrayCopy.int2byte(fiBytesAsInt);


                String filename = "myfile";
                //ImgUtils.byteArrtoFile(fiBytes, filename);
                //String fileContents = "Hello world!";

                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(fiBytes);
                    outputStream.close();
                    Log.d("saved","FILE SAVED");
                } catch (Exception e) {
                    e.printStackTrace();
                }


                //research android filesystem

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













