package com.example.app.user;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/")
	public String homePage() {
		return "index";
	}
	
	@GetMapping("/login")
	public String loginPage(Authentication auth) {
		if (auth.getPrincipal() != null) {
			System.out.println(auth.getPrincipal());
			return "redirect:/messagerooms";
		}
		return "login";
	}
	
	@GetMapping("/signup")
	public String signupPage() {
		return "signup";
	}
	
}
