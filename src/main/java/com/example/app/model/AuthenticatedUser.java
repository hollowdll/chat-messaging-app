package com.example.app.model;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticatedUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	private AppUser appUser;
	
	public AuthenticatedUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return appUser.getHashedPassword();
	}

	@Override
	public String getUsername() {
		return appUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
	
	public int getId() {
		return appUser.getAppUserId();
	}
	
	public LocalDateTime getCreated() {
		return appUser.getCreated();
	}
	
}
