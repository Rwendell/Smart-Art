package com.example.austinsehnert.smartart;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.austinsehnert.smartart.utils.ArrayCopy;
import com.example.austinsehnert.smartart.utils.ImgUtils;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * This class extends the class Actvity, and allows for the websocket connection
 * on the client side.
 */
public class WebSocket extends Activity {
    public WebSocketClient mWebSocketClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectWebSocket();


        /*if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
    }

    /**
     * Inflate the menu; this adds items to the action bar if it is present.
     * @param menu menu in which item is too be added to
     * @return return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

   /* @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);

    }*/


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }


       /* @Override

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;

        }*/

    }

    /**
     * This class actually connects the websockets to the server
     */
    public void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://proj-309-sb-2.cs.iastate.edu:8080/board/mainBoard");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            /**
             * Establishes connection to server and sends message
             * @param serverHandshake handshake param confirms that server is correct
             */
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                SocketSingleton.setSocket(mWebSocketClient);

                Log.i("Websocket", "Opened");
                // mWebSocketClient.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
                mWebSocketClient.send("{\"drawElement\": \"John\"}");
            }

            /**
             * Gets data back from server, converts it as an image, and saves the file
             * @param s message received from server
             */
            @Override
            public void onMessage(String s) {
                final String message = s;

                String fc = s;//s.substring(6);

                String[] fcArr = fc.split("\\s+");

                int[] fiBytesAsInt = new int[fc.length()];
                for (int i = 0; i < fcArr.length; i++) {
                    fiBytesAsInt[i] = Integer.parseInt(fcArr[i]);
                }
                byte[] fiBytes = ArrayCopy.int2byte(fiBytesAsInt);;

                fiBytes = ArrayCopy.int2byte(fiBytesAsInt);

                ImgUtils.byteArrtoFile(fiBytes, "test.png");

                System.out.println("file saved!");


                System.out.println(s);;
               /* runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        TextView textView = (TextView)findViewById(R.id.messages);
                        textView.setText(textView.getText() + "\n" + message);
                    }

                });*/

            }

            /**
             * Lets you know in the log if the connection closes
             */
            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            /**
             * Lets you know if there is an error that occured
             * @param e specific exception being handled
             */
            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();

        //mWebSocketClient.send

        //SocketSingleton.setSocket(mWebSocketClient);

    }

}

