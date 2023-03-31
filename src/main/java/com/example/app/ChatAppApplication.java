package com.example.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.app.message.Message;
import com.example.app.message.MessageDAO;
import com.example.app.messageroom.MessageRoom;
import com.example.app.messageroom.MessageRoomDAO;
import com.example.app.user.AppUser;
import com.example.app.user.AppUserDAO;

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
		MessageRoomDAO messageRoomDAO,
		MessageDAO messageDAO
	) {
		return (args) -> {
			// Create test data
			
			System.out.println();
			log.info("Creating test objects...");
			
			log.info("Creating test users...");
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
			
			String username = "Testuser1";
			String password = "Testuser1";
			String hashedPassword = passwordEncoder.encode(password);
			AppUser appUser = new AppUser(username, hashedPassword);
			appUser.setAppUserId(1);
			
			String username2 = "Testuser2";
			String password2 = "Testuser2";
			String hashedPassword2 = passwordEncoder.encode(password2);
			AppUser appUser2 = new AppUser(username2, hashedPassword2);
			appUser2.setAppUserId(2);
			
			log.info("Creating test message room...");
			MessageRoom messageRoom = new MessageRoom("Test message room", appUser);
			messageRoom.setMessageRoomId(1);
			
			log.info("Creating test message...");
			Message message = new Message("Test message 1", appUser, messageRoom);
			message.setMessageId(1);
			
			Message message2 = new Message("Test message 2", appUser2, messageRoom);
			message.setMessageId(2);
			
			// Save test data
			
			System.out.println();
			log.info("Saving test user to database...");
			appUserDAO.save(appUser);
			appUserDAO.save(appUser2);
			
			log.info("Saving test message room to database...");
			messageRoomDAO.save(messageRoom);
			
			log.info("Savinf test message to database...");
			messageDAO.save(message);
			messageDAO.save(message2);
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
	
	@Bean
	@Order(4)
	public CommandLineRunner fetchTestMessage(MessageDAO messageDAO) {
		return (args) -> {
			System.out.println();
			log.info("Fetching test message from database...");
			
			System.out.println(messageDAO.findById(1).get());
		};
	}
	
}
