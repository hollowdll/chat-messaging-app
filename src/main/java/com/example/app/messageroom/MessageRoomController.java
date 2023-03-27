package com.example.app.messageroom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.messageroommember.MessageRoomMember;
import com.example.app.messageroommember.MessageRoomMemberDAO;
import com.example.app.user.AuthenticatedUser;

@Controller
public class MessageRoomController {
	
	private final MessageRoomDAO messageRoomDAO;
	private final MessageRoomMemberDAO messageRoomMemberDAO;
	
	@Autowired
	public MessageRoomController(MessageRoomDAO messageRoomDAO, MessageRoomMemberDAO messageRoomMemberDAO) {
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
	
}
