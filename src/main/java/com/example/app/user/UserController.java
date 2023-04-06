package com.example.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private final AppUserDAO appUserDAO;
	
	@Autowired
	public UserController(AppUserDAO appUserDAO) {
		this.appUserDAO = appUserDAO;
	}

	// Home page
	@GetMapping("/")
	public String homePage() {
		return "index";
	}
	
	// Login page
	@GetMapping("/login")
	public String loginPage(Authentication auth) {
		if (auth != null) {
			return "redirect:/messagerooms";
		}
		return "login";
	}
	
	// Sign up page
	@GetMapping("/signup")
	public String signupPage(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		return "signup";
	}
	
	// Sign up form submit
	@PostMapping("/signup")
	public String signupSubmit(
		@Valid @ModelAttribute("signupForm") SignupForm signupForm,
		BindingResult bindingResult
	) {
		// Validation errors
		if (bindingResult.hasErrors()) {
			return "signup";			
		}
		
		// If confirm password matches
		if (signupForm.getPassword().equals(signupForm.getPasswordConfirm())) {
			String password = signupForm.getPassword();
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
			String passwordHash = passwordEncoder.encode(password);
			
			String username = signupForm.getUsername().trim();
			AppUser appUser = new AppUser(username, passwordHash);
			
			// If user exists
			if (appUserDAO.findByUsername(username).isEmpty()) {
				appUserDAO.save(appUser);
			} else {
				bindingResult.rejectValue("username", "err.username", "Username already exists");
				return "signup";
			}
		} else {
			bindingResult.rejectValue("passwordConfirm", "err.passwordMatch", "Password does not match");
			return "signup";
		}
		
		return "redirect:/signup?success";
	}
	
}
