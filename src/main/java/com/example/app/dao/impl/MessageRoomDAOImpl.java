package com.example.app.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.app.dao.AppUserDAO;
import com.example.app.dao.MessageRoomDAO;
import com.example.app.mapper.MessageRoomRowMapper;
import com.example.app.model.MessageRoom;

@Repository
public class MessageRoomDAOImpl implements MessageRoomDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AppUserDAO appUserDAO;
	
	public void save(MessageRoom messageRoom) {
		String sql = """
			INSERT INTO message_rooms (name, user_id, password, created)
			VALUES (?,?,?,?)
			""";
		
		Object[] parameters = new Object[] {
			messageRoom.getName(),
			messageRoom.getOwner().getAppUserId(),
			messageRoom.getHashedPassword(),
			messageRoom.getCreated()
		};
		
		jdbcTemplate.update(sql, parameters);
	}
	
	public List<MessageRoom> findAll() {
		String sql = """
			SELECT name, user_id, password, created
			FROM message_rooms
			LIMIT 100
			""";
		
		RowMapper<MessageRoom> mapper = new MessageRoomRowMapper(appUserDAO);
		List<MessageRoom> messageRooms = jdbcTemplate.query(sql, mapper);
		
		return messageRooms;
	}
	
	public Optional<MessageRoom> findById(long id) {
		String sql = """
			SELECT message_room_id, name, user_id, password, created
			FROM message_rooms
			WHERE message_room_id = ?
			""";
		
		RowMapper<MessageRoom> mapper = new MessageRoomRowMapper(appUserDAO);
		Optional<MessageRoom> messageRoom = jdbcTemplate.query(sql, mapper, id)
			.stream()
			.findFirst();
		
		return messageRoom;
	}
	
	public void deleteById(long id) {
		String sql = """
			DELETE FROM message_rooms
			WHERE message_room_id = ?
			""";
		
		jdbcTemplate.update(sql, id);
	}
	
	public void updateById(long id, MessageRoom messageRoom) {
		String sql = """
			UPDATE message_rooms
			SET name = ?, password = ?
			WHERE message_room_id = ?
			""";
		
		Object[] parameters = new Object[] {
				messageRoom.getName(),
				messageRoom.getHashedPassword(),
		};
			
		jdbcTemplate.update(sql, parameters, id);
	}
	
}
