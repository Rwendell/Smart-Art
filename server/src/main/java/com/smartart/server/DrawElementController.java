package com.smartart.server;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class DrawElementController {

    @MessageMapping("/draw/{board_id}")
    @SendTo("/draw")
    public DrawElement drawelement(String content) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new DrawElement(content);
    }



}
