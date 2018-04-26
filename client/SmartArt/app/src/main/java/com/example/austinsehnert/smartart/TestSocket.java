package com.example.austinsehnert.smartart;

import org.java_websocket.client.WebSocketClient;


/**
 * Created by Paul on 4/22/18.
 */



public class TestSocket {

    public WebSocketClient w = SocketSingleton.getSocket();

    public void testSend() {
        w.send("tests");
    }
}
