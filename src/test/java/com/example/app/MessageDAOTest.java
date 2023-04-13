package com.example.app;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.message.Message;
import com.example.app.message.MessageDAO;
import com.example.app.messageroom.MessageRoom;
import com.example.app.messageroom.MessageRoomDAO;
import com.example.app.user.AppUser;
import com.example.app.user.AppUserDAO;

// Test data for this test class is created in ChatAppApplication.java
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MessageDAOTest {

	private final MessageDAO messageDAO;
	private final MessageRoomDAO messageRoomDAO;
	private final AppUserDAO appUserDAO;
	
	@BeforeEach
	public void setup() {
		
	}
	
	@Autowired
	public MessageDAOTest(MessageDAO messageDAO, MessageRoomDAO messageRoomDAO, AppUserDAO appUserDAO) {
		this.messageDAO = messageDAO;
		this.messageRoomDAO = messageRoomDAO;
		this.appUserDAO = appUserDAO;
	}
	
	@Test
	public void findAllMessages() throws Exception {
		List<Message> messages = messageDAO.findAll();

		assertThat(messages).isInstanceOf(List.class);
		assertThat(messages).isNotEmpty();
	}
	
	@Test
	public void findAllMessagesByMessageRoomId() throws Exception {
		List<Message> messages = messageDAO.findAllByMessageRoomId(1);
		
		assertThat(messages).isInstanceOf(List.class);
		assertThat(messages).isNotEmpty();
	}
	
	@Test
	public void createMessage() throws Exception {
		int messageCount = messageDAO.findAll().size();
		AppUser sender = appUserDAO.findByUsername("UserForTesting1").orElseThrow();
		MessageRoom messageRoom = messageRoomDAO.findById(1).orElseThrow();
		Message message = new Message("Test message created by test", sender, messageRoom);
		messageDAO.save(message);
		
		assertTrue(messageDAO.findAll().size() > messageCount);
	}
	
	@Test
	public void createMessageWithSenderId() throws Exception {
		int messageCount = messageDAO.findAll().size();
		AppUser sender = appUserDAO.findByUsername("UserForTesting1").orElseThrow();
		MessageRoom messageRoom = messageRoomDAO.findById(1).orElseThrow();
		Message message = new Message("Test message created by test", sender, messageRoom);
		messageDAO.saveWithSenderId(message, sender.getAppUserId());
		
		assertTrue(messageDAO.findAll().size() > messageCount);
	}
	
	@Test
	public void deleteMessage() throws Exception {
		messageDAO.deleteById(2);
		
		assertThat(messageDAO.findById(2)).isEmpty();
	}
	
	@Test
	public void updateMessage() throws Exception {
		Message message = messageDAO.findById(1).orElseThrow();
		String oldText = message.getText();
		message.setText("Updated message");
		messageDAO.updateById(1, message);
		
		assertThat(messageDAO.findById(1).orElseThrow().getText()).isNotEqualTo(oldText);
	}
	
}
