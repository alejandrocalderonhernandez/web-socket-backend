package com.alejandro.socket.controller;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.alejandro.socket.model.document.Message;
import com.alejandro.socket.service.MessageService;

@Controller
public class ChatController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);
	private static final String[] COLORS = {"red", "green", "blue", "magenta", "purple", "orange"};
	

	private SimpMessagingTemplate template;
	private MessageService service;
	
	@Autowired
	public ChatController( SimpMessagingTemplate template, MessageService service  ) {
		this.template = template;
		this.service = service;
	}
	
	@MessageMapping("/message")
	@SendTo("/send/message")
	public Message  reciveMessage(Message message) {
		message.setDate(new Date().getTime());
		if(message.getType().equals("NEW_USER")) {
			message.setColor(COLORS[new Random().nextInt(COLORS.length)]);
			message.setText("New user ");
		} else {
			this.service.save(message);
		}
		message.setText("New message " + message.getText());
		LOGGER.info(message.getText());
		return message;
	}
	
	@MessageMapping("/writing")
	@SendTo("/send/writing")
	public String  writing(String username) {
		return username.concat("esta escribiendo...");
	}
	
	@MessageMapping("/history")
	public void  getLastMessages(String id) {
		template.convertAndSend("/send/hystory/" + id, this.service.getLast10Messages());
	}

}
