package com.example.app.messageroom;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.app.user.AppUserDAO;

@Repository
public class MessageRoomDAOImpl implements MessageRoomDAO {

	private final JdbcTemplate jdbcTemplate;
	private final AppUserDAO appUserDAO;
	
	@Autowired
	public MessageRoomDAOImpl(JdbcTemplate jdbcTemplate, AppUserDAO appUserDAO) {
		this.jdbcTemplate = jdbcTemplate;
		this.appUserDAO = appUserDAO;
	}
	
	public void save(MessageRoom messageRoom) {
		String sql = """
			INSERT INTO message_rooms (name, user_id, created)
			VALUES (?,?,?)
			""";
		
		Object[] parameters = new Object[] {
			messageRoom.getName(),
			messageRoom.getOwner().getAppUserId(),
			messageRoom.getCreated()
		};
		
		jdbcTemplate.update(sql, parameters);
	}
	
	public void saveWithOwnerId(MessageRoom messageRoom, int appUserId) {
		String sql = """
			INSERT INTO message_rooms (name, user_id, created)
			VALUES (?,?,?)
			""";
		
		Object[] parameters = new Object[] {
			messageRoom.getName(),
			appUserId,
			messageRoom.getCreated()
		};
		
		jdbcTemplate.update(sql, parameters);
	}
	
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
	
	public void deleteById(int id) {
		String sql = """
			DELETE FROM message_rooms
			WHERE message_room_id = ?
			""";
		
		jdbcTemplate.update(sql, id);
	}
	
	public void updateById(int id, MessageRoom messageRoom) {
		String sql = """
			UPDATE message_rooms
			SET name = ?
			WHERE message_room_id = ?
			""";
			
		jdbcTemplate.update(sql, messageRoom.getName(), id);
	}
	
}
