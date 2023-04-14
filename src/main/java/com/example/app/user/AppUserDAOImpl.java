package com.example.app.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppUserDAOImpl implements AppUserDAO {

	private final JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;
	
	@Autowired
	public AppUserDAOImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
		this.jdbcTemplate = jdbcTemplate;
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
			.withTableName("users")
			.usingGeneratedKeyColumns("user_id");
	}
	
	@Transactional
	public int save(AppUser appUser) {
		Map<String, Object> parameters = new HashMap<>(3);
		parameters.put("username", appUser.getUsername());
		parameters.put("password", appUser.getHashedPassword());
		parameters.put("created", appUser.getCreated());
		
		return (int) simpleJdbcInsert.executeAndReturnKey(parameters);
	}
	
	@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = true)
	public Optional<AppUser> findByUsername(String username) {
		String sql = """
			SELECT user_id, username, password, created
			FROM users
			WHERE username = ?
			""";
		
		RowMapper<AppUser> mapper = new AppUserRowMapper();
		Optional<AppUser> appUser = jdbcTemplate.query(sql, mapper, username)
			.stream()
			.findFirst();
		
		return appUser;
	}
	
}
