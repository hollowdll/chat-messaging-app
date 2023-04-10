package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.user.AppUser;
import com.example.app.user.AppUserDAO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AppUserDAOTest {

	private final AppUserDAO appUserDAO;
	
	@Autowired
	public AppUserDAOTest(AppUserDAO appUserDAO) {
		this.appUserDAO = appUserDAO;
	}
	
	@Test
	public void findUserByUsername() throws Exception {
		String username = "Testuser1";
		Optional<AppUser> appUser = appUserDAO.findByUsername(username);
		
		assertThat(appUser).isPresent();
		assertThat(appUser.orElseThrow().getUsername()).isEqualTo(username);
	}
	
	@Test
	public void findUserById() throws Exception {
		
	}
	
	@Test
	public void findAllUsers() throws Exception {
		
	}
	
	@Test
	public void createUser() throws Exception {
		
	}
	
	@Test
	public void deleteUser() throws Exception {
		
	}
	
	@Test
	public void updateUser() throws Exception {
		
	}
	
}
