package com.example.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.message.MessageDAO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MessageDAOTest {

	private final MessageDAO messageDAO;
	
	@Autowired
	public MessageDAOTest(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}
	
	@Test
	public void findAllMessages() throws Exception {
		
	}
	
	@Test
	public void findAllMessagesByMessageRoomId() throws Exception {
		
	}
	
	@Test
	public void createMessage() throws Exception {
		
	}
	
	@Test
	public void createMessageWithSenderId() throws Exception {
		
	}
	
	@Test
	public void deleteMessage() throws Exception {
		
	}
	
	@Test
	public void deleteAllMessageByMessageRoomId() throws Exception {
		
	}
	
	@Test
	public void updateMessage() throws Exception {
		
	}
	
}
