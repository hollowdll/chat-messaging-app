package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.messageroom.MessageRoom;
import com.example.app.messageroom.MessageRoomDAO;
import com.example.app.user.AppUser;
import com.example.app.user.AppUserDAO;

// Test data for this test class is created in ChatAppApplication.java
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MessageRoomDAOTest {

	private final MessageRoomDAO messageRoomDAO;
	private final AppUserDAO appUserDAO;
	
	@Autowired
	public MessageRoomDAOTest(MessageRoomDAO messageRoomDAO, AppUserDAO appUserDAO) {
		this.messageRoomDAO = messageRoomDAO;
		this.appUserDAO = appUserDAO;
	}
	
	@Test
	public void findMessageRoomById() throws Exception {
		int id = 1;
		Optional<MessageRoom> messageRoom = messageRoomDAO.findById(id);
		
		assertThat(messageRoom).isPresent();
		assertThat(messageRoom.orElseThrow().getMessageRoomId()).isEqualTo(id);
	}
	
	@Test
	public void findAllMessageRooms() throws Exception {
		List<MessageRoom> messageRooms = messageRoomDAO.findAll();
		
		assertThat(messageRooms).isInstanceOf(List.class);
		assertThat(messageRooms).isNotEmpty();
	}
	
	@Test
	public void createMessageRoom() throws Exception {
		AppUser owner = appUserDAO.findByUsername("UserForTesting1").orElseThrow();
		MessageRoom messageRoom = new MessageRoom("Message room for test 1", owner);
		messageRoom.setMessageRoomId(2);
		messageRoomDAO.save(messageRoom);
		
		assertThat(messageRoomDAO.findById(messageRoom.getMessageRoomId())).isPresent();
	}
	
	@Test
	public void createMessageRoomWithOwnerId() throws Exception {
		AppUser owner = appUserDAO.findByUsername("UserForTesting1").orElseThrow();
		MessageRoom messageRoom = new MessageRoom("Message room for test 2", owner);
		messageRoom.setMessageRoomId(3);
		messageRoomDAO.saveWithOwnerId(messageRoom, owner.getAppUserId());
		
		assertThat(messageRoomDAO.findById(messageRoom.getMessageRoomId())).isPresent();
	}
	
	@Test
	public void deleteMessageRoom() throws Exception {
		AppUser owner = appUserDAO.findByUsername("UserForTesting1").orElseThrow();
		MessageRoom messageRoom = new MessageRoom("Message room for test 3", owner);
		messageRoom.setMessageRoomId(4);
		messageRoomDAO.save(messageRoom);
		messageRoomDAO.deleteById(4);
		
		assertThat(messageRoomDAO.findById(4)).isEmpty();
	}
	
	@Test
	public void updateMessageRoom() throws Exception {
		MessageRoom messageRoom = messageRoomDAO.findById(1).orElseThrow();
		String oldName = messageRoom.getName();
		messageRoom.setName("Updated message room");
		messageRoomDAO.updateById(1, messageRoom);
		
		assertThat(messageRoomDAO.findById(1).orElseThrow().getName()).isNotEqualTo(oldName);
	}
	
}
