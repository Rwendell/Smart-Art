package com.smartart.server;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
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
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    sessions.remove(session);
    }

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


        try {
            session.sendMessage(new BinaryMessage(message.getPayload()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {


        sessions.add(session);
        //essentially this should be taking a file and converting it to a byte array
        //remove if not working
        File fi = new File("/resources" + session.getUri() + ".png");
        byte[] fileContent = Files.readAllBytes(fi.toPath());
        //System.out.println(Arrays.toString(fileContent));
        session.sendMessage(new BinaryMessage(fileContent));
    }

}