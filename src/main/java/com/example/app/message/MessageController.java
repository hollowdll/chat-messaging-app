package com.example.app.message;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.app.messageroom.MessageRoom;
import com.example.app.messageroom.MessageRoomDAO;
import com.example.app.user.AuthenticatedUser;

import jakarta.validation.Valid;

@Controller
public class MessageController {
	
	private final MessageDAO messageDAO;
	private final MessageRoomDAO messageRoomDAO;
	
	@Autowired
	public MessageController(MessageDAO messageDAO, MessageRoomDAO messageRoomDAO) {
		this.messageDAO = messageDAO;
		this.messageRoomDAO = messageRoomDAO;
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
		Message fetchedMessage = messageDAO.findById(messageId).orElse(null);
		
		if (fetchedMessage == null) {
			return "error";
		}
		
		if (bindingResult.hasErrors()) {
			// Show validation error messages
			List<String> errorMessages = new ArrayList<String>();
			bindingResult.getAllErrors().forEach((error) -> {
				errorMessages.add(error.getDefaultMessage());
			});
			
			model.addAttribute("errorMessages", errorMessages);
			model.addAttribute("message", fetchedMessage);
			
			return "editmessage";
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
	
	// WebSocket STOMP messages
	// Receive input messages and
	// send output message to all subscribed clients
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public OutputMessage sendMessageToSubscribedClientsInRealtime(
		@Valid @RequestBody InputMessage inputMessage,
		Authentication auth
	) throws Exception {
		// If message room exists
		MessageRoom messageRoom = messageRoomDAO.findById(inputMessage.getMessageRoomId()).orElse(null);
		
		if (messageRoom == null) {
			throw new Exception("Message room not found");
		}
		
		// Get authenticated user
		AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
		String username = authenticatedUser.getUsername();
		int appUserId = authenticatedUser.getUserId();
		
		// Save message to database
		Message message = new Message();
		message.setText(inputMessage.getText());
		message.setMessageRoom(messageRoom);
		message.setCreated(LocalDateTime.now());
		String formattedTime = message.formatCreated();
		
		messageDAO.saveWithSenderId(message, appUserId);
		
		return new OutputMessage(inputMessage.getText(), username, formattedTime, messageRoom.getMessageRoomId());
	}
	
}
