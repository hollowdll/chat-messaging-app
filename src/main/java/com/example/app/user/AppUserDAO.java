package com.example.app.user;

import java.util.List;
import java.util.Optional;

// Database operations for users
public interface AppUserDAO {
	
	// Saves a new user
	// Returns generated id (primary key)
	public int save(AppUser appUser);
	
	// Finds all users
	public List<AppUser> findAll();
	
	// Finds a user by id
	public Optional<AppUser> findById(int id);
	
	// Finds a user by username
	public Optional<AppUser> findByUsername(String username);
	
}
