package com.example.app.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class AppUser {

	private Long appUserId;
	private String username;
	private String hashedPassword;
	private LocalDateTime created;
	
	public AppUser() {
		
	}

	public AppUser(String username, String hashedPassword) {
		this.username = username;
		this.hashedPassword = hashedPassword;
		created = LocalDateTime.now(ZoneOffset.UTC);
	}

	public Long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "AppUser [appUserId=" + appUserId + ", username=" + username + ", hashedPassword=" + hashedPassword
				+ ", created=" + created + "]";
	}
	
}
