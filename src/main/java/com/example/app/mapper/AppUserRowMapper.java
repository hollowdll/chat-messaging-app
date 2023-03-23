package com.example.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.app.model.AppUser;

public class AppUserRowMapper implements RowMapper<AppUser> {

	public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		AppUser appUser = new AppUser();
		appUser.setAppUserId(rs.getInt("user_id"));
		appUser.setUsername(rs.getString("username"));
		appUser.setHashedPassword(rs.getString("password"));
		appUser.setCreated(rs.getTimestamp("created").toLocalDateTime());
		
		return appUser;
	}
	
}
