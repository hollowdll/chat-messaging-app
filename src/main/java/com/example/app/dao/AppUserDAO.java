package com.example.app.dao;

import java.util.List;
import java.util.Optional;

import com.example.app.model.AppUser;

// Database operations for users
public interface AppUserDAO {
	
	public void save(AppUser appUser);
	public List<AppUser> findAll();
	public Optional<AppUser> findById(int id);
	public void deleteById(int id);
	public void updateById(int id, AppUser appUser);
	
}
