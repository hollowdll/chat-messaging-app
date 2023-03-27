package com.example.app.user;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthenticatedUser extends User {

	private static final long serialVersionUID = 1L;
	private int userId;
	private LocalDateTime created;

	public AuthenticatedUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
			int userId, LocalDateTime created) {
		super(username, password, authorities);
		this.userId = userId;
		this.created = created;
	}

	public int getUserId() {
		return userId;
	}
	
	public LocalDateTime getCreated() {
		return created;
	}

	@Override
	public String toString() {
		return "AuthenticatedUser [userId=" + userId + ", created=" + created + ", getPassword()=" + getPassword()
				+ ", getUsername()=" + getUsername() + "]";
	}
	
}
