package com.example.app.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/")
	public String homePage() {
		return "index";
	}
	
	@GetMapping("/testuser")
	public String continueWithTestUser() {
		return "redirect:/rooms";
	}
	
}
