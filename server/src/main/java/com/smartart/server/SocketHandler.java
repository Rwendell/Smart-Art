package com.smartart.server;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();



    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);


		for(WebSocketSession webSocketSession : sessions) {
		    //grabs everything with drawElement
			webSocketSession.sendMessage(new TextMessage(value.get("drawElement")));
		}
        //session.sendMessage(new TextMessage(value.get("drawElement")));
    }

    //Remove if not working
    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {

        //should send binary files okay
        try {
            session.sendMessage(new BinaryMessage(message.getPayload()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //the messages will be broadcasted to all users.

        /*TODO: find out how this works

        I understand that this is used to send messages, but I need it to handle specific cases, this works too
        generically.

         */

        sessions.add(session);
        //essentially this should be taking a file and converting it to a byte array
        //remove if not working
        File fi = new File("/resources" + session.getUri() + ".png");
        byte[] fileContent = Files.readAllBytes(fi.toPath());
        session.sendMessage(new BinaryMessage(fileContent));
    }

}