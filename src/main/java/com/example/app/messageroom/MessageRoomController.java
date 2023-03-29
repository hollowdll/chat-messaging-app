package com.example.app.messageroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.app.message.Message;
import com.example.app.message.MessageDAO;
import com.example.app.messageroommember.MessageRoomMember;
import com.example.app.messageroommember.MessageRoomMemberDAO;
import com.example.app.user.AuthenticatedUser;

@Controller
public class MessageRoomController {
	
	private final MessageDAO messageDAO;
	private final MessageRoomDAO messageRoomDAO;
	private final MessageRoomMemberDAO messageRoomMemberDAO;
	
	@Autowired
	public MessageRoomController(
		MessageDAO messageDAO,
		MessageRoomDAO messageRoomDAO,
		MessageRoomMemberDAO messageRoomMemberDAO
	) {
		this.messageDAO = messageDAO;
		this.messageRoomDAO = messageRoomDAO;
		this.messageRoomMemberDAO = messageRoomMemberDAO;
	}

	@GetMapping("/messagerooms")
	public String messageRoomsPage(Model model, Authentication auth) {
		// Get authenticated user
		AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
		int appUserId = authenticatedUser.getUserId();
		
		// Get user's message rooms
		List<MessageRoom> messageRooms = new ArrayList<MessageRoom>();
		List<MessageRoomMember> messageRoomMembers = messageRoomMemberDAO.findAllByAppUserId(appUserId);
		for (MessageRoomMember messageRoomMember : messageRoomMembers) {
			messageRooms.add(messageRoomMember.getMessageRoom());
		}
		
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
	
}
