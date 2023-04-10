package com.example.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.messageroom.MessageRoomDAO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MessageRoomDAOTest {

	private final MessageRoomDAO messageRoomDAO;
	
	@Autowired
	public MessageRoomDAOTest(MessageRoomDAO messageRoomDAO) {
		this.messageRoomDAO = messageRoomDAO;
	}
	
	@Test
	public void findMessageRoomById() throws Exception {
		
	}
	
	@Test
	public void findAllMessageRooms() throws Exception {
		
	}
	
	@Test
	public void createMessageRoom() throws Exception {
		
	}
	
	@Test
	public void createMessageRoomWithOwnerId() throws Exception {
		
	}
	
	@Test
	public void deleteMessageRoom() throws Exception {
		
	}
	
	@Test
	public void updateMessageRoom() throws Exception {
		
	}
	
}
