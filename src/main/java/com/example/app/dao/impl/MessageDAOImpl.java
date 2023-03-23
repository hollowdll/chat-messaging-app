package com.example.app.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.app.dao.AppUserDAO;
import com.example.app.dao.MessageDAO;
import com.example.app.dao.MessageRoomDAO;
import com.example.app.mapper.MessageRowMapper;
import com.example.app.model.Message;

public class MessageDAOImpl implements MessageDAO {

	private final JdbcTemplate jdbcTemplate;
	private final AppUserDAO appUserDAO;
	private final MessageRoomDAO messageRoomDAO;
	
	@Autowired
	public MessageDAOImpl(
		JdbcTemplate jdbcTemplate,
		AppUserDAO appUserDAO,
		MessageRoomDAO messageRoomDAO
	) {
		this.jdbcTemplate = jdbcTemplate;
		this.appUserDAO = appUserDAO;
		this.messageRoomDAO = messageRoomDAO;
	}
	
	public void save(Message message) {
		String sql = """
			INSERT INTO messages (text, user_id, message_room_id, created)
			VALUES (?,?,?,?)
			""";
		
		Object[] parameters = new Object[] {
			message.getText(),
			message.getSender().getAppUserId(),
			message.getMessageRoom().getMessageRoomId(),
			message.getCreated()
		};
		
		jdbcTemplate.update(sql, parameters);
	}
	
	public List<Message> findAll() {
		String sql = """
			SELECT message_id, text, user_id, message_room_id, created
			FROM messages
			LIMIT 50
			""";
		
		RowMapper<Message> mapper = new MessageRowMapper(appUserDAO, messageRoomDAO);
		List<Message> messages = jdbcTemplate.query(sql, mapper);
		
		return messages;
	}
	
	public Optional<Message> findById(long id) {
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
	
	public void deleteById(long id) {
		String sql = """
			DELETE FROM messages
			WHERE message_id = ?
			""";
		
		jdbcTemplate.update(sql, id);
	}
	
	public void updateById(long id, Message message) {
		String sql = """
			UPDATE messages
			SET text = ?
			WHERE message_id = ?
			""";
		
		Object[] parameters = new Object[] {
			message.getText()
		};
			
		jdbcTemplate.update(sql, parameters, id);
	}
	
}