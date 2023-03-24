package com.example.app.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/")
	public String homePage() {
		return "index";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/testuser")
	public String continueWithTestUser() {
		return "redirect:/messagerooms";
	}
	
}
