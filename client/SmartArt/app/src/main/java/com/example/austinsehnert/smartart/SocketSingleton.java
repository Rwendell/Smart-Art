package com.example.austinsehnert.smartart;

import org.java_websocket.client.WebSocketClient;

/**
 * Created by Paul on 4/22/18.
 */

public class SocketSingleton {


    public static WebSocketClient socket;



    public static void setSocket(WebSocketClient ws) {
        SocketSingleton.socket = ws;

    }

    public static WebSocketClient getSocket() {

        return SocketSingleton.socket;
    }

    public static void sendMessage(String message){

        socket.send(message);

    }

}