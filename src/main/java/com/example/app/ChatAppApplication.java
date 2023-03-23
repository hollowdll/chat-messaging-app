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
import com.example.app.dao.MessageRoomDAO;
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
	
	// CommandLineRunners will run at application startup.
	// They create and save test data into the database
	// They are mainly used for testing.
	
	@Bean
	@Order(1)
	public CommandLineRunner createAndSaveTestEntities(
		AppUserDAO appUserDAO,
		MessageRoomDAO messageRoomDAO
	) {
		return (args) -> {
			// Create test data
			
			System.out.println();
			log.info("Creating test objects...");
			
			log.info("Creating test user...");
			AppUser appUser = new AppUser("Test user 1", "testuser1");
			appUser.setAppUserId(1);
			
			log.info("Creating test message room...");
			MessageRoom messageRoom = new MessageRoom("Test message room", appUser, "testmessageroom");
			messageRoom.setMessageRoomId(1);
			
			log.info("Creating test message room member...");
			MessageRoomMember messageRoomMember = new MessageRoomMember(messageRoom, appUser);
			
			log.info("Creating test message...");
			Message message = new Message("Test message 123", appUser, messageRoom);
			message.setMessageId(1);
			
			System.out.println(appUser);
			System.out.println(messageRoom);
			System.out.println(messageRoomMember);
			System.out.println(message);
			
			// Save test data
			
			System.out.println();
			log.info("Saving test user to database...");
			appUserDAO.save(appUser);
			
			log.info("Saving test message room to database...");
			messageRoomDAO.save(messageRoom);
			
			
		};
	}
	
	@Bean
	@Order(2)
	public CommandLineRunner fetchTestUser(AppUserDAO appUserDAO) {
		return (args) -> {
			System.out.println();
			log.info("Fetching all users from database...");
			
			List<AppUser> appUsers = appUserDAO.findAll();
			
			for (AppUser fetchedUser : appUsers) {
				System.out.println(fetchedUser);
			}
			
			log.info("Fetching test user from database...");
			
			System.out.println(appUserDAO
				.findById(appUsers.stream()
					.findFirst()
					.get()
					.getAppUserId())
				.get());
		};
	}
	
	@Bean
	@Order(3)
	public CommandLineRunner fetchTestMessageRoom(MessageRoomDAO messageRoomDAO) {
		return (args) -> {
			System.out.println();
			log.info("Fetching test message room from database");
			
			System.out.println(messageRoomDAO.findById(1).get());
		};
	}
	
}
