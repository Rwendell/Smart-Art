package com.smartart.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
/**
 * @author rwendell
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        //just make different handlers then you should be able to handle each thing differently

        registry.addHandler(new SocketHandler(), "/board/{id}");

    }
}
