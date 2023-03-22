package com.example.app.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.dao.AppUserDAO;
import com.example.app.mapper.AppUserRowMapper;
import com.example.app.model.AppUser;

@Repository
public class AppUserDAOImpl implements AppUserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// Save user to the database
	@Transactional(rollbackFor = { SQLException.class }, readOnly = false)
	public void save(AppUser appUser) {
		String sql = "INSERT INTO users (username, password, created) VALUES (?,?,?)";
		
		Object[] parameters = new Object[] {
			appUser.getUsername(),
			appUser.getHashedPassword(),
			appUser.getCreated()
		};
		
		jdbcTemplate.update(sql, parameters);
	}
	
	// Find all users in the database
	@Transactional(readOnly = true)
	public List<AppUser> findAll() {
		String sql = "SELECT user_id, username, password, created FROM users";
		RowMapper<AppUser> mapper = new AppUserRowMapper();
		List<AppUser> appUsers = jdbcTemplate.query(sql, mapper);
		
		return appUsers;
	}
	
}
