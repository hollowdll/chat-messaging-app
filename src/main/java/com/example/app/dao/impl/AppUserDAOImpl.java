package com.example.app.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.app.dao.AppUserDAO;
import com.example.app.mapper.AppUserRowMapper;
import com.example.app.model.AppUser;

@Repository
public class AppUserDAOImpl implements AppUserDAO {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public AppUserDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void save(AppUser appUser) {
		String sql = """
			INSERT INTO users (username, password, created)
			VALUES (?,?,?)
			""";
		
		Object[] parameters = new Object[] {
			appUser.getUsername(),
			appUser.getHashedPassword(),
			appUser.getCreated()
		};
		
		jdbcTemplate.update(sql, parameters);
	}
	
	public List<AppUser> findAll() {
		String sql = """
			SELECT user_id, username, password, created
			FROM users
			LIMIT 50
			""";
		
		RowMapper<AppUser> mapper = new AppUserRowMapper();
		List<AppUser> appUsers = jdbcTemplate.query(sql, mapper);
		
		return appUsers;
	}
	
	public Optional<AppUser> findById(int id) {
		String sql = """
			SELECT user_id, username, password, created
			FROM users
			WHERE user_id = ?
			""";
		
		RowMapper<AppUser> mapper = new AppUserRowMapper();
		Optional<AppUser> appUser = jdbcTemplate.query(sql, mapper, id)
			.stream()
			.findFirst();
		
		return appUser;
	}
	
	public void deleteById(int id) {
		String sql = """
			DELETE FROM users
			WHERE user_id = ?
			""";
		
		jdbcTemplate.update(sql, id);
	}
	
	public void updateById(int id, AppUser appUser) {
		String sql = """
			UPDATE users
			SET username = ?, password = ?
			WHERE user_id = ?
			""";
		
		Object[] parameters = new Object[] {
			appUser.getUsername(),
			appUser.getHashedPassword()
		};
			
		jdbcTemplate.update(sql, parameters, id);
	}
	
}
