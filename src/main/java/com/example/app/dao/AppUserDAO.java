package com.example.app.dao;

import java.util.List;
import java.util.Optional;

import com.example.app.model.AppUser;

// Database operations for users
public interface AppUserDAO {
	
	public void save(AppUser appUser);
	public List<AppUser> findAll();
	public Optional<AppUser> findById(long id);
	public void deleteById(long id);
	public void updateById(long id, AppUser appUser);
	
}
