package com.example.app.messageroommember;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.app.messageroom.MessageRoomDAO;
import com.example.app.user.AppUserDAO;

@Repository
public class MessageRoomMemberDAOImpl implements MessageRoomMemberDAO {

	private final JdbcTemplate jdbcTemplate;
	private final MessageRoomDAO messageRoomDAO;
	private final AppUserDAO appUserDAO;
	
	@Autowired
	public MessageRoomMemberDAOImpl(
		JdbcTemplate jdbcTemplate,
		MessageRoomDAO messageRoomDAO,
		AppUserDAO appUserDAO
	) {
		this.jdbcTemplate = jdbcTemplate;
		this.appUserDAO = appUserDAO;
		this.messageRoomDAO = messageRoomDAO;
	}
	
	public void save(MessageRoomMember messageRoomMember) {
		String sql = """
			INSERT INTO message_room_members (message_room_id, user_id, joined)
			VALUES (?,?,?)
			""";
		
		Object[] parameters = new Object[] {
			messageRoomMember.getMessageRoom().getMessageRoomId(),
			messageRoomMember.getMember().getAppUserId(),
			messageRoomMember.getJoined()
		};
		
		jdbcTemplate.update(sql, parameters);
	}
	
	public List<MessageRoomMember> findAll() {
		String sql = """
			SELECT message_room_id, user_id, joined
			FROM message_room_members
			LIMIT 50
			""";
		
		RowMapper<MessageRoomMember> mapper = new MessageRoomMemberRowMapper(messageRoomDAO, appUserDAO);
		List<MessageRoomMember> messageRoomMembers = jdbcTemplate.query(sql, mapper);
		
		return messageRoomMembers;
	}
	
	public Optional<MessageRoomMember> findById(int messageRoomId, int appUserId) {
		String sql = """
			SELECT message_room_id, user_id, joined
			FROM message_room_members
			WHERE message_room_id = ?
			AND user_id = ?
			""";
		
		RowMapper<MessageRoomMember> mapper = new MessageRoomMemberRowMapper(messageRoomDAO, appUserDAO);
		Optional<MessageRoomMember> messageRoomMember = jdbcTemplate.query(sql, mapper, messageRoomId, appUserId)
			.stream()
			.findFirst();
		
		return messageRoomMember;
	}
	
	public List<MessageRoomMember> findAllByAppUserId(int appUserId) {
		String sql = """
			SELECT message_room_id, user_id, joined
			FROM message_room_members
			WHERE user_id = ?
			""";
		
		RowMapper<MessageRoomMember> mapper = new MessageRoomMemberRowMapper(messageRoomDAO, appUserDAO);
		List<MessageRoomMember> messageRoomMembers = jdbcTemplate.query(sql, mapper, appUserId);
		
		return messageRoomMembers;
	}
	
	public void deleteById(int messageRoomId, int appUserId) {
		String sql = """
			DELETE FROM message_room_members
			WHERE message_room_id = ?
			AND user_id = ?
			""";
		
		jdbcTemplate.update(sql, messageRoomId, appUserId);
	}
	
}
