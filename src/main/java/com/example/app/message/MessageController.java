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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.user.AuthenticatedUser;

import jakarta.validation.Valid;

@Controller
public class MessageController {
	
	private final MessageDAO messageDAO;
	
	@Autowired
	public MessageController(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}
	
	// Create message by sending it
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
		
		AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
		int appUserId = authenticatedUser.getUserId();
		
		message.setCreated(LocalDateTime.now());
		messageDAO.saveWithSenderId(message, appUserId);
		
		return "redirect:/messagerooms/" + message.getMessageRoom().getMessageRoomId();
	}
	
	// Message edit page
	@GetMapping("/editmessage/{id}")
	public String editMessagePage(@PathVariable("id") int messageId, Authentication auth, Model model) {
		Message fetchedMessage = messageDAO.findById(messageId).orElse(null);
		
		if (fetchedMessage == null) {
			return "error";
		}
		
		// Get authenticated user
		AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
		String username = authenticatedUser.getUsername();
		
		// If user is the sender of this message
		if (!username.equals(fetchedMessage.getSender().getUsername())) {
			return "redirect:/messagerooms/" + fetchedMessage.getMessageRoom().getMessageRoomId();
		}
		
		model.addAttribute("message", fetchedMessage);
		
		return "editmessage";
	}
	
	// Message edit submit
	@PostMapping("/editmessage/{id}")
	public String editMessageSubmit(
		@PathVariable("id") int messageId,
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
			
			return "editmessage";
		}
		
		Message fetchedMessage = messageDAO.findById(messageId).orElse(null);
		
		if (fetchedMessage == null) {
			return "error";
		}
		
		// Get authenticated user
		AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
		String username = authenticatedUser.getUsername();
		
		// If user is the sender of this message
		if (!username.equals(fetchedMessage.getSender().getUsername())) {
			return "redirect:/messagerooms/" + fetchedMessage.getMessageRoom().getMessageRoomId();
		}
		
		messageDAO.updateById(messageId, message);
		
		return "redirect:/messagerooms/" + fetchedMessage.getMessageRoom().getMessageRoomId();
	}
	
	// Delete message
	@GetMapping("/deletemessage/{id}")
	public String deleteMessage(@PathVariable("id") int messageId, Authentication auth) {
		Message fetchedMessage = messageDAO.findById(messageId).orElse(null);
		
		if (fetchedMessage == null) {
			return "error";
		}
		
		// Get authenticated user
		AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
		String username = authenticatedUser.getUsername();
		
		// If user is the sender of this message
		if (!username.equals(fetchedMessage.getSender().getUsername())) {
			return "redirect:/messagerooms/" + fetchedMessage.getMessageRoom().getMessageRoomId();
		}
		
		messageDAO.deleteById(messageId);
		
		return "redirect:/messagerooms/" + fetchedMessage.getMessageRoom().getMessageRoomId();
	}
	
	/*
	// WebSocket STOMP messages
	// Receive input messages and
	// send response message to all subscribed clients
	public Message sendMessageInRealtime(Message message, Authentication auth) throws Exception {
		
	}
	*/
	
}
