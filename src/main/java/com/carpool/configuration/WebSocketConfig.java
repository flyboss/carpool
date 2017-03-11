package com.carpool.configuration;

import com.carpool.website.service.RoomWebSocketHandler;
import com.carpool.website.service.UnreadWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

/**
 * Created by deado on 2016/12/21.
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(roomWebSocketHandler(),"/webSocketServer").addInterceptors(new WebSocketInterceptor());

        registry.addHandler(unreadWebSocketHandler(),"/unreadWebSocketServer").addInterceptors(new WebSocketInterceptor());

        registry.addHandler(roomWebSocketHandler(), "/sockjs/webSocketServer").addInterceptors(new WebSocketInterceptor())
                .withSockJS();
    }

    @Bean
    public WebSocketHandler roomWebSocketHandler(){
        return new RoomWebSocketHandler();
    }

    @Bean
    public WebSocketHandler unreadWebSocketHandler(){ return new UnreadWebSocketHandler();}

}