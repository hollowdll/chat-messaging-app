package com.example.app.messageroom;

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

import com.example.app.user.AppUserDAO;

@Repository
public class MessageRoomDAOImpl implements MessageRoomDAO {

	private final JdbcTemplate jdbcTemplate;
	private final AppUserDAO appUserDAO;
	private final SimpleJdbcInsert simpleJdbcInsert;
	
	@Autowired
	public MessageRoomDAOImpl(JdbcTemplate jdbcTemplate, AppUserDAO appUserDAO, DataSource dataSource) {
		this.jdbcTemplate = jdbcTemplate;
		this.appUserDAO = appUserDAO;
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
			.withTableName("message_rooms")
			.usingGeneratedKeyColumns("message_room_id");
	}
	
	@Transactional
	public int save(MessageRoom messageRoom) {
		Map<String, Object> parameters = new HashMap<>(3);
		parameters.put("name", messageRoom.getName());
		parameters.put("user_id", messageRoom.getOwner().getAppUserId());
		parameters.put("created", messageRoom.getCreated());
		
		return (int) simpleJdbcInsert.executeAndReturnKey(parameters);
	}
	
	@Transactional
	public int saveWithOwnerId(MessageRoom messageRoom, int appUserId) {
		Map<String, Object> parameters = new HashMap<>(3);
		parameters.put("name", messageRoom.getName());
		parameters.put("user_id", appUserId);
		parameters.put("created", messageRoom.getCreated());
		
		return (int) simpleJdbcInsert.executeAndReturnKey(parameters);
	}
	
	@Transactional(readOnly = true)
	public List<MessageRoom> findAll() {
		String sql = """
			SELECT message_room_id, name, user_id, created
			FROM message_rooms
			ORDER BY message_room_id ASC
			""";
		
		RowMapper<MessageRoom> mapper = new MessageRoomRowMapper(appUserDAO);
		List<MessageRoom> messageRooms = jdbcTemplate.query(sql, mapper);
		
		return messageRooms;
	}
	
	@Transactional(readOnly = true)
	public Optional<MessageRoom> findById(int id) {
		String sql = """
			SELECT message_room_id, name, user_id, created
			FROM message_rooms
			WHERE message_room_id = ?
			""";
		
		RowMapper<MessageRoom> mapper = new MessageRoomRowMapper(appUserDAO);
		Optional<MessageRoom> messageRoom = jdbcTemplate.query(sql, mapper, id)
			.stream()
			.findFirst();
		
		return messageRoom;
	}
	
	@Transactional
	public void deleteById(int id) {
		String sql = """
			DELETE FROM message_rooms
			WHERE message_room_id = ?
			""";
		
		jdbcTemplate.update(sql, id);
	}
	
	@Transactional
	public void updateById(int id, MessageRoom messageRoom) {
		String sql = """
			UPDATE message_rooms
			SET name = ?
			WHERE message_room_id = ?
			""";
			
		jdbcTemplate.update(sql, messageRoom.getName(), id);
	}
	
}
