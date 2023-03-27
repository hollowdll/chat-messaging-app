package com.example.app.messageroom;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.messageroommember.MessageRoomMemberDAO;

import jakarta.servlet.http.HttpServletRequest;

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
	public String messageRoomsPage(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		System.out.println(principal);
		
		// messageRoomMemberDAO.findAllByAppUserId(0);
		
		
		return "messagerooms";
	}
	
}
