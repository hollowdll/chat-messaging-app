package com.example.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.message.MessageDAO;

// Test data for this test class is created in ChatAppApplication.java
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
		assertTrue(false);
	}
	
	@Test
	public void findAllMessagesByMessageRoomId() throws Exception {
		assertTrue(false);
	}
	
	@Test
	public void createMessage() throws Exception {
		assertTrue(false);
	}
	
	@Test
	public void createMessageWithSenderId() throws Exception {
		assertTrue(false);
	}
	
	@Test
	public void deleteMessage() throws Exception {
		assertTrue(false);
	}
	
	@Test
	public void deleteAllMessageByMessageRoomId() throws Exception {
		assertTrue(false);
	}
	
	@Test
	public void updateMessage() throws Exception {
		assertTrue(false);
	}
	
}
