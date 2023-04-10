package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.message.MessageController;
import com.example.app.messageroom.MessageRoomController;
import com.example.app.user.UserController;
import com.example.app.user.UserDetailsServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SmokeTest {

	private final UserController userController;
	private final MessageRoomController messageRoomController;
	private final MessageController messageController;
	private final UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	public SmokeTest(
		UserController userController,
		MessageRoomController messageRoomController,
		MessageController messageController,
		UserDetailsServiceImpl userDetailsService
	) {
		this.userController = userController;
		this.messageRoomController = messageRoomController;
		this.messageController = messageController;
		this.userDetailsService = userDetailsService;
	}
	
	@Test
	public void userControllerLoads() throws Exception {
		assertThat(userController).isNotNull();
	}
	
	@Test
	public void messageRoomControllerLoads() throws Exception {
		assertThat(messageRoomController).isNotNull();
	}
	
	@Test
	public void messageControllerLoads() throws Exception {
		assertThat(messageController).isNotNull();
	}
	
	@Test
	public void userDetailsServiceLoads() throws Exception {
		assertThat(userDetailsService).isNotNull();
	}
	
}
