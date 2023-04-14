package com.example.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
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
	// They create and save test data to the database
	// They are mainly used for testing.
	
	@Bean
	@Order(1)
	public CommandLineRunner createTestUsers(
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
			
			String username2 = "Testuser2";
			String password2 = "Testuser2";
			String hashedPassword2 = passwordEncoder.encode(password2);
			AppUser appUser2 = new AppUser(username2, hashedPassword2);
			
			// Save test data
			
			System.out.println();
			log.info("Saving test user to database...");
			appUserDAO.save(appUser);
			appUserDAO.save(appUser2);
		};
	}
	
	@Bean
	@Order(2)
	public CommandLineRunner fetchTestUsers(AppUserDAO appUserDAO) {
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
	
	// This is used only in tests. It saves data to test database that tests can use.
	// set environment variable 'SPRING_PROFILES_ACTIVE=testing' to execute this
	@Bean
	@Order(3)
	@Profile("testing")
	public CommandLineRunner initDataForTests(
		AppUserDAO appUserDAO,
		MessageRoomDAO messageRoomDAO,
		MessageDAO messageDAO
	) {
		return (args) -> {
			System.out.println();
			log.info("Creating test user...");
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
			String username = "UserForTesting1";
			String password = "UserForTesting1";
			String hashedPassword = passwordEncoder.encode(password);
			AppUser appUser = new AppUser(username, hashedPassword);
			appUser.setAppUserId(3);
			
			log.info("Creating test message rooms...");
			MessageRoom messageRoom1 = new MessageRoom("Test message room 1", appUser);
			messageRoom1.setMessageRoomId(1);
			MessageRoom messageRoom2 = new MessageRoom("Test message room 2", appUser);
			messageRoom2.setMessageRoomId(2);
			
			log.info("Creating test message...");
			Message message = new Message("Test message 1", appUser, messageRoom1);
			message.setMessageId(1);
			
			log.info("Saving test user to database...");
			appUserDAO.save(appUser);
			
			log.info("Saving test message room2 to database...");
			messageRoomDAO.save(messageRoom1);
			messageRoomDAO.save(messageRoom2);
			
			log.info("Saving test message to database...");
			messageDAO.save(message);
		};
	}
	
}
