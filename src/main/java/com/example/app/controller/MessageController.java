package com.example.app.controller;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.model.Message;

@Controller
public class MessageController {

	@GetMapping("/")
	public String showMessage(Model model) {
		Message message = new Message(
			"Test message",
			0,
			0,
			LocalDateTime
				.now(ZoneOffset.UTC)
				.format(DateTimeFormatter.ofPattern("d/MM/uuuu HH:mm:ss"))
				+ " UTC+0"
		);
		
		model.addAttribute("message", message);
		return "messagelist";
	}
	
}
