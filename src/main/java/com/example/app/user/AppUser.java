package com.example.app.user;

import java.time.LocalDateTime;

public class AppUser {

	private int appUserId;
	private String username;
	private String hashedPassword;
	private LocalDateTime created;
	
	public AppUser() {}

	public AppUser(String username, String hashedPassword) {
		appUserId = 0;
		this.username = username;
		this.hashedPassword = hashedPassword;
		created = LocalDateTime.now();
	}

	public int getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(int appUserId) {
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
