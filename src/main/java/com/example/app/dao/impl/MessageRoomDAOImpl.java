package com.example.app.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.mapper.AppUserRowMapper;
import com.example.app.mapper.MessageRoomRowMapper;
import com.example.app.model.AppUser;
import com.example.app.model.MessageRoom;

@Repository
public class MessageRoomDAOImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// Save entity to the database and rollback in case of an error
	@Transactional(rollbackFor = { SQLException.class }, readOnly = false)
	public void save(MessageRoom messageRoom) {
		String sql = "INSERT INTO message_rooms (name, user_id, password, created) VALUES (?,?,?,?)";
		
		Object[] parameters = new Object[] {
			messageRoom.getName(),
			messageRoom.getOwner().getAppUserId(),
			messageRoom.getHashedPassword(),
			messageRoom.getCreated()
		};
		
		jdbcTemplate.update(sql, parameters);
	}
	
	// Find all entities in the database
	@Transactional(readOnly = true)
	public List<MessageRoom> findAll() {
		String sql = "SELECT name, user_id, password, created FROM message_rooms";
		RowMapper<MessageRoom> mapper = new MessageRoomRowMapper();
		List<MessageRoom> messageRooms = jdbcTemplate.query(sql, mapper);
		
		return messageRooms;
	}
	
}
