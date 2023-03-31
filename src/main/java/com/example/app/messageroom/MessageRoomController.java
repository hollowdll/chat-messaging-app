package com.example.app.messageroom;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.message.Message;
import com.example.app.message.MessageDAO;
import com.example.app.user.AuthenticatedUser;

@Controller
public class MessageRoomController {
	
	private final MessageDAO messageDAO;
	private final MessageRoomDAO messageRoomDAO;
	
	@Autowired
	public MessageRoomController(MessageDAO messageDAO, MessageRoomDAO messageRoomDAO) {
		this.messageDAO = messageDAO;
		this.messageRoomDAO = messageRoomDAO;
	}

	@GetMapping("/messagerooms")
	public String messageRoomsPage(Model model, Authentication auth) {
		// Get message rooms
		List<MessageRoom> messageRooms = messageRoomDAO.findAll();
		model.addAttribute("messageRooms", messageRooms);
		
		return "messagerooms";
	}
	
	@GetMapping("/messagerooms/{id}")
	public String chatPage(Model model, @PathVariable("id") int messageRoomId) {
		// Check if message room exists
		MessageRoom messageRoom = messageRoomDAO.findById(messageRoomId).orElse(null);
		
		if (messageRoom == null) {
			return "404";
		}
		
		// Find message history
		List<Message> messages = messageDAO.findAllByMessageRoomId(messageRoomId);
		
		model.addAttribute("messageRoomName", messageRoom.getName());
		model.addAttribute("messages", messages);
		
		return "chat";
	}
	
	@GetMapping("/createmessageroom")
	public String createMessageRoomPage(Model model) {
		model.addAttribute("messageRoom", new MessageRoom());
		return "createmessageroom";
	}
	
	@PostMapping("/createmessageroom")
	public String createMessageRoomSubmit(@ModelAttribute MessageRoom messageRoom, Authentication auth) {
		// Get authenticated user
		AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
		int appUserId = authenticatedUser.getUserId();
		
		messageRoom.setCreated(LocalDateTime.now());
		messageRoomDAO.saveWithOwnerId(messageRoom, appUserId);
		
		return "redirect:/messagerooms";
	}
	
}
