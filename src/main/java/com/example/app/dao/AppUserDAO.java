package com.example.app.dao;

import java.util.List;
import com.example.app.model.AppUser;

public interface AppUserDAO {
	
	public void save(AppUser appUser);
	public List<AppUser> findAll();
	
}
