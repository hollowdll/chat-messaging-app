package com.example.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import com.example.app.dao.AppUserDAO;
import com.example.app.model.AppUser;
import com.example.app.model.Message;
import com.example.app.model.MessageRoom;
import com.example.app.model.MessageRoomMember;

@SpringBootApplication
public class ChatAppApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ChatAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ChatAppApplication.class, args);
	}
	
	@Bean
	@Order(1)
	public CommandLineRunner logTestObjects() {
		return (args) -> {
			System.out.println();
			log.info("Creating test objects...");
			
			log.info("Creating test user...");
			AppUser appUser = new AppUser("Test user 1", "testuser1");
			
			log.info("Creating test message room...");
			MessageRoom messageRoom = new MessageRoom("Test message room", appUser, "testmessageroom");
			
			log.info("Creating message room member...");
			MessageRoomMember messageRoomMember = new MessageRoomMember(messageRoom, appUser);
			
			log.info("Creating test message...");
			Message message = new Message("Test message 123", appUser, messageRoom);
			
			System.out.println(appUser);
			System.out.println(messageRoom);
			System.out.println(messageRoomMember);
			System.out.println(message);
		};
	}
	
	@Bean
	@Order(2)
	public CommandLineRunner createAndSaveTestUser(AppUserDAO appUserDAO) {
		return (args) -> {
			System.out.println();
			log.info("Creating and saving test user to database...");
			
			// No password hashing yet
			AppUser appUser = new AppUser("Testuser123", "Testuser123password");
			appUserDAO.save(appUser);
			
			log.info("Fetching all users from database...");
			
			List<AppUser> appUsers = appUserDAO.findAll();
			
			for (AppUser fetchedUser : appUsers) {
				System.out.println(fetchedUser);
			}
		};
	}
	
	
}
