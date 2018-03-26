package com.smartart.server;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * @author rwendell
 */
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

        //client says they disconnect before they do
        if(value.containsKey("userDisconnected")){
            String fc = value.get("userDisconnectednb");

            String[] fcArr = fc.split("\\s+");

            int[] fiBytesAsInt = new int[fc.length()];
            for(int i = 0;i < fc.length();i++)
            {
                fiBytesAsInt[i] = Integer.parseInt(fcArr[i]);
            }

            byte[] fiBytes;
            fiBytes = ArrayCopy.int2byte(fiBytesAsInt);
            File fi = new File("/resources" + session.getUri() + ".png");
            Files.write(fi.toPath(),fiBytes,
                    StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING );


        }


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


        /*                                  PRIMARY PLAN (Commented out for demo purposes)                         */


        //session.sendMessage((new BinaryMessage(fileContent)));


        /*                                  BACKUP PLAN                                                            */

        //easier than using Arrays.toString() and formatting the string
        String fc = new String();
        for(byte c : fileContent) {
            fc = fc + String.format("%d ", c);
        }

        /*

        Session.sendMessage((new TextMessage( <Payload> ))) has it's own JSON format
        No need to format it as a JSON

        if Necessary:
        JSONObject pL = new JSONObject();//payload
        pL.put("boardSnapshot", fc);
        session.sendMessage(new TextMessage(pL.toString()));

        */



        session.sendMessage((new TextMessage( fc )));

        /*
            making fc into an array again:

            String[] fcArr = fc.split("\\s+");

            int[] fiBytes = new int[fc.length];
            for(int i = 0;i < fc.length;i++)
            {
               fiBytes[i] = Integer.parseInt(fcArr[i]);
            }
         */
    }

}