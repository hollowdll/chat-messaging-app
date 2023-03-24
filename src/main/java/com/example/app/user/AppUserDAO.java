package com.example.app.user;

import java.util.List;
import java.util.Optional;

// Database operations for users
public interface AppUserDAO {
	
	public void save(AppUser appUser);
	public List<AppUser> findAll();
	public Optional<AppUser> findById(int id);
	public Optional<AppUser> findByUsername(String username);
	public void deleteById(int id);
	public void updateById(int id, AppUser appUser);
	
}
