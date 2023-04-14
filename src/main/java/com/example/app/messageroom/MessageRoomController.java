package com.example.app.messageroom;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.message.Message;
import com.example.app.message.MessageDAO;
import com.example.app.user.AuthenticatedUser;

import jakarta.validation.Valid;

@Controller
public class MessageRoomController {
	
	private final MessageDAO messageDAO;
	private final MessageRoomDAO messageRoomDAO;
	
	@Autowired
	public MessageRoomController(MessageDAO messageDAO, MessageRoomDAO messageRoomDAO) {
		this.messageDAO = messageDAO;
		this.messageRoomDAO = messageRoomDAO;
	}
	
	// Check if user is the owner of message room
	private String checkMessageRoomOwner(MessageRoom fetchedMessageRoom, Authentication auth) {
		// Get authenticated user
		AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
		String username = authenticatedUser.getUsername();
		
		// If user is the owner of this message room
		if (!username.equals(fetchedMessageRoom.getOwner().getUsername())) {
			return "redirect:/messagerooms";
		}
		
		return "ok";
	}

	// Message rooms page
	@GetMapping("/messagerooms")
	public String messageRoomsPage(Authentication auth, Model model) {
		// Get message rooms
		List<MessageRoom> messageRooms = messageRoomDAO.findAll();
		model.addAttribute("messageRooms", messageRooms);
		
		return "messagerooms";
	}
	
	// Chat page
	@GetMapping("/messagerooms/{id}")
	public String chatPage(@PathVariable("id") int messageRoomId, Model model) {
		// Check if message room exists
		MessageRoom messageRoom = messageRoomDAO.findById(messageRoomId).orElse(null);
		
		if (messageRoom == null) {
			return "error";
		}
		
		// Find message history
		List<Message> messageHistory = messageDAO.findAllByMessageRoomId(messageRoomId);
		
		// set message model
		Message message = new Message();
		message.setMessageRoom(messageRoom);
		
		model.addAttribute("messageRoom", messageRoom);
		model.addAttribute("messageRoomName", messageRoom.getName());
		model.addAttribute("messageHistory", messageHistory);
		model.addAttribute("message", message);
		
		return "chatrealtime";
	}
	
	// Message room create page
	@GetMapping("/createmessageroom")
	public String createMessageRoomPage(Model model) {
		model.addAttribute("messageRoom", new MessageRoom());
		
		return "createmessageroom";
	}
	
	// Message room create submit
	@PostMapping("/createmessageroom")
	public String createMessageRoomSubmit(
		@Valid @ModelAttribute MessageRoom messageRoom,
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
			
			return "createmessageroom";
		}
		
		AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
		int appUserId = authenticatedUser.getUserId();
		
		messageRoom.setCreated(LocalDateTime.now());
		messageRoomDAO.saveWithOwnerId(messageRoom, appUserId);
		
		return "redirect:/messagerooms";
	}
	
	// Message room edit page
	@GetMapping("/editmessageroom/{id}")
	public String editMessageRoomPage(@PathVariable("id") int messageRoomId, Authentication auth, Model model) {
		// Check if message room exists
		MessageRoom fetchedMessageRoom = messageRoomDAO.findById(messageRoomId).orElse(null);
		
		if (fetchedMessageRoom == null) {
			return "error";
		}
		
		// If user is allowed to edit
		String result = checkMessageRoomOwner(fetchedMessageRoom, auth);
		if (result != "ok") {
			return result;
		}
		
		model.addAttribute("messageRoom", fetchedMessageRoom);
		
		return "editmessageroom";
	}
	
	// Message room edit submit
	@PostMapping("/editmessageroom/{id}")
	public String editMessageRoomSubmit(
		@PathVariable("id") int messageRoomId,
		@Valid @ModelAttribute MessageRoom messageRoom,
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
			
			return "editmessageroom";
		}
		
		// Check if message room exists
		MessageRoom fetchedMessageRoom = messageRoomDAO.findById(messageRoomId).orElse(null);
		
		if (fetchedMessageRoom == null) {
			return "error";
		}
		
		String result = checkMessageRoomOwner(fetchedMessageRoom, auth);
		if (result != "ok") {
			return result;
		}
		
		messageRoomDAO.updateById(messageRoomId, messageRoom);
		
		return "redirect:/messagerooms";
	}
	
	// Delete message room
	@GetMapping("/deletemessageroom/{id}")
	public String deleteMessageRoom(@PathVariable("id") int messageRoomId, Authentication auth) {
		// Check if message room exists
		MessageRoom fetchedMessageRoom = messageRoomDAO.findById(messageRoomId).orElse(null);
		
		if (fetchedMessageRoom == null) {
			return "error";
		}
		
		// If user is allowed to delete
		String result = checkMessageRoomOwner(fetchedMessageRoom, auth);
		if (result != "ok") {
			return result;
		}
		
		messageDAO.deleteAllByMessageRoomId(messageRoomId);
		messageRoomDAO.deleteById(messageRoomId);
		
		return "redirect:/messagerooms";
	}
	
}
