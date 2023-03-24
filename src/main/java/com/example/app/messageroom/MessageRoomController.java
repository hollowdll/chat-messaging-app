package com.example.app.messageroom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageRoomController {

	@GetMapping("/messagerooms")
	public String messageRoomsPage() {
		return "messagerooms";
	}
	
}
