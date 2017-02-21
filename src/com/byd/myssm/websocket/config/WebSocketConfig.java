package com.byd.myssm.websocket.config;

import com.byd.myssm.websocket.hndler.SystemWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.byd.myssm.websocket.interceptor.HandshakeInterceptor;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	//TODO WebSocket消息通知功能
    public WebSocketConfig() {
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(systemWebSocketHandler(), "/websck").addInterceptors(new HandshakeInterceptor());

        System.out.println("registed!");
        registry.addHandler(systemWebSocketHandler(), "/sockjs/websck/info")
        		.addInterceptors(new HandshakeInterceptor())
                .withSockJS();
    }

    @Bean
    public WebSocketHandler systemWebSocketHandler() {
        //return new InfoSocketEndPoint();
        return new SystemWebSocketHandler();
    }

}
