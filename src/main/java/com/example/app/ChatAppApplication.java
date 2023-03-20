package com.example.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatAppApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ChatAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ChatAppApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return (args) -> {
			log.info("Test message...");
			
			log.info("(DISABLED) Creating test user...");
			
			log.info("(DISABLED) Creating test message room...");
			
			log.info("(DISABLED) Creating test message...");
		};
	}

}
