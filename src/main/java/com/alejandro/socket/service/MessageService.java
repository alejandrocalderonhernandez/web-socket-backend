package com.alejandro.socket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.socket.model.document.Message;
import com.alejandro.socket.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository repocitory;
	
	public List<Message> getLast10Messages() {
		return this.repocitory.findFirst10ByOrderByDateDesc();
	}
	
	public Message save(Message message) {
		return this.repocitory.save(message);
	}

}
