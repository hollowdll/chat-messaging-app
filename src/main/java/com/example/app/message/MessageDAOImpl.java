package com.example.app.message;

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

import com.example.app.messageroom.MessageRoomDAO;
import com.example.app.user.AppUserDAO;

@Repository
public class MessageDAOImpl implements MessageDAO {

	private final JdbcTemplate jdbcTemplate;
	private final AppUserDAO appUserDAO;
	private final MessageRoomDAO messageRoomDAO;
	private final SimpleJdbcInsert simpleJdbcInsert;
	
	@Autowired
	public MessageDAOImpl(
		JdbcTemplate jdbcTemplate,
		AppUserDAO appUserDAO,
		MessageRoomDAO messageRoomDAO,
		DataSource dataSource
	) {
		this.jdbcTemplate = jdbcTemplate;
		this.appUserDAO = appUserDAO;
		this.messageRoomDAO = messageRoomDAO;
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
			.withTableName("messages")
			.usingGeneratedKeyColumns("message_id");
	}
	
	@Transactional
	public int save(Message message) {
		Map<String, Object> parameters = new HashMap<>(4);
		parameters.put("text", message.getText());
		parameters.put("user_id", message.getSender().getAppUserId());
		parameters.put("message_room_id", message.getMessageRoom().getMessageRoomId());
		parameters.put("created", message.getCreated());
		
		return (int) simpleJdbcInsert.executeAndReturnKey(parameters);
	}
	
	@Transactional
	public int saveWithSenderId(Message message, int appUserId) {
		Map<String, Object> parameters = new HashMap<>(4);
		parameters.put("text", message.getText());
		parameters.put("user_id", appUserId);
		parameters.put("message_room_id", message.getMessageRoom().getMessageRoomId());
		parameters.put("created", message.getCreated());
		
		return (int) simpleJdbcInsert.executeAndReturnKey(parameters);
	}
	
	@Transactional(readOnly = true)
	public List<Message> findAll() {
		String sql = """
			SELECT message_id, text, user_id, message_room_id, created
			FROM messages
			ORDER BY message_id ASC
			""";
		
		RowMapper<Message> mapper = new MessageRowMapper(appUserDAO, messageRoomDAO);
		List<Message> messages = jdbcTemplate.query(sql, mapper);
		
		return messages;
	}
	
	@Transactional(readOnly = true)
	public List<Message> findAllByMessageRoomId(int id) {
		String sql = """
			SELECT message_id, text, user_id, message_room_id, created
			FROM messages
			WHERE message_room_id = ?
			ORDER BY message_id ASC
			""";
		
		RowMapper<Message> mapper = new MessageRowMapper(appUserDAO, messageRoomDAO);
		List<Message> messages = jdbcTemplate.query(sql, mapper, id);
		
		return messages;
	}
	
	@Transactional(readOnly = true)
	public Optional<Message> findById(int id) {
		String sql = """
			SELECT message_id, text, user_id, message_room_id, created
			FROM messages
			WHERE message_id = ?
			""";
		
		RowMapper<Message> mapper = new MessageRowMapper(appUserDAO, messageRoomDAO);
		Optional<Message> message = jdbcTemplate.query(sql, mapper, id)
			.stream()
			.findFirst();
		
		return message;
	}
	
	@Transactional
	public void deleteById(int id) {
		String sql = """
			DELETE FROM messages
			WHERE message_id = ?
			""";
		
		jdbcTemplate.update(sql, id);
	}
	
	@Transactional
	public void deleteAllByMessageRoomId(int id) {
		String sql = """
			DELETE FROM messages
			WHERE message_room_id = ?
			""";
		
		jdbcTemplate.update(sql, id);
	}
	
	@Transactional
	public void updateById(int id, Message message) {
		String sql = """
			UPDATE messages
			SET text = ?
			WHERE message_id = ?
			""";
			
		jdbcTemplate.update(sql, message.getText(), id);
	}
	
}
