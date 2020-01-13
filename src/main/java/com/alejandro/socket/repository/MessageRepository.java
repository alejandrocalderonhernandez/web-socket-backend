package com.alejandro.socket.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.alejandro.socket.model.document.Message;

public interface MessageRepository  extends MongoRepository<Message, String> {
	
	public List<Message> findFirst10ByOrderByDateDesc();

}
