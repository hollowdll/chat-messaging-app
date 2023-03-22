package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.model.AppUser;
import com.example.app.model.Message;
import com.example.app.model.MessageRoom;

@Controller
public class MessageController {

	@GetMapping("/messages")
	public String showTestMessage(Model model) {
		Message message = new Message(
			"Test message 123",
			new AppUser(),
			new MessageRoom()
		);
		
		model.addAttribute("message", message);
		return "messagelist";
	}
	
}
