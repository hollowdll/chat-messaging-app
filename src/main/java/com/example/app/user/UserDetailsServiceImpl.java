package com.example.app.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AppUserDAO appUserDAO;
	
	@Autowired
	public UserDetailsServiceImpl(AppUserDAO appUserDAO) {
		this.appUserDAO = appUserDAO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> appUser = appUserDAO.findByUsername(username);
		
		if (appUser.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
	
		return new AuthenticatedUser(appUser.get());
	}
	
}
