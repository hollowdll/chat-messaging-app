package com.example.app.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupForm {

	@NotBlank(message="Username cannot be blank")
	@Size(min=1, max=30, message="Username length must be between 1 and 30")
	private String username;
	
	@NotBlank(message="Password cannot be blank")
	@Size(min=8, max=50, message="Password length must be between 8 and 50")
	private String password;
	
	@NotBlank(message="Password cannot be blank")
	@Size(min=8, max=50, message="Password length must be between 8 and 50")
	private String passwordConfirm;
	
	public SignupForm() {
		username = "";
		password = "";
		passwordConfirm = "";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	@Override
	public String toString() {
		return "SignupForm [username=" + username + ", password=" + password + ", passwordConfirm=" + passwordConfirm
				+ "]";
	}
	
}
