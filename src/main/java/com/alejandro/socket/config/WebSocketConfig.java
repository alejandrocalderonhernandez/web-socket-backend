package com.alejandro.socket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	private static final String END_POINT_CHAT = "/chat";
	private static final String ORIGINS = "http://localhost:4200";
	private static final String EVENT_PREFIX = "/broker/";
	private static final String DESTINATION_PREFIX = "/app";

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry
		.addEndpoint(END_POINT_CHAT)
		.setAllowedOrigins(ORIGINS)
		.withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
	 registry .enableSimpleBroker(EVENT_PREFIX);
	 registry.setApplicationDestinationPrefixes(DESTINATION_PREFIX);
	}
	
}
