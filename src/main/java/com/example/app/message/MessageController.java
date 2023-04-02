package com.example.app.message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.messageroom.MessageRoom;
import com.example.app.user.AppUser;
import com.example.app.user.AuthenticatedUser;

import jakarta.validation.Valid;

@Controller
public class MessageController {
	
	private final MessageDAO messageDAO;
	
	@Autowired
	public MessageController(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

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
	
	@PostMapping("/createmessage")
	public String createMessage(
		@Valid @ModelAttribute Message message,
		BindingResult bindingResult,
		Authentication auth,
		Model model
	) {
		if (bindingResult.hasErrors()) {
			// Show validation error messages
			List<String> errorMessages = new ArrayList<String>();
			bindingResult.getAllErrors().forEach((error) -> {
				errorMessages.add(error.getDefaultMessage());
			});
			
			model.addAttribute("errorMessages", errorMessages);
			
			return "chat";
		}
		System.out.println(message.getText());
		System.out.println(message.getMessageRoom().getMessageRoomId());
		
		AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
		int appUserId = authenticatedUser.getUserId();
		
		message.setCreated(LocalDateTime.now());
		messageDAO.saveWithSenderId(message, appUserId);
		
		return "redirect:/messagerooms/" + message.getMessageRoom().getMessageRoomId();
	}
	
}
