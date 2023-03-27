package com.example.app.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthenticatedUser extends User {

	private static final long serialVersionUID = 1L;
	private AppUser appUser;

	public AuthenticatedUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
			AppUser appUser) {
		super(username, password, authorities);
		this.appUser = appUser;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	@Override
	public String toString() {
		return "AuthenticatedUser [appUser=" + appUser + "]";
	}
	
}
