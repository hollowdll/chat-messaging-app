package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public void createUser() throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
		String password = "UserForTesting";
		String hashedPassword = passwordEncoder.encode(password);
		String username = "UserForTesting";
		
		AppUser appUser = new AppUser(username, hashedPassword);
		appUserDAO.save(appUser);
		Optional<AppUser> expectedAppUser = appUserDAO.findByUsername(username);
		
		assertThat(expectedAppUser).isPresent();
		assertThat(expectedAppUser.orElseThrow().getUsername()).isEqualTo(username);
		assertThat(expectedAppUser.orElseThrow().getHashedPassword()).isEqualTo(hashedPassword);
	}
	
	@Test
	public void findUserByUsername() throws Exception {
		String expectedUsername = "Testuser1";
		Optional<AppUser> appUser = appUserDAO.findByUsername(expectedUsername);
		
		assertThat(appUser).isPresent();
		assertThat(appUser.orElseThrow().getUsername()).isEqualTo(expectedUsername);
	}
	
	@Test
	public void findUserById() throws Exception {
		int expectedId = 1;
		Optional<AppUser> appUser = appUserDAO.findById(expectedId);
		
		assertThat(appUser).isPresent();
		assertThat(appUser.orElseThrow().getAppUserId()).isEqualTo(expectedId);
	}
	
	@Test
	public void findAllUsers() throws Exception {
		List<AppUser> appUsers = appUserDAO.findAll();
		
		assertThat(appUsers).isInstanceOf(List.class);
		assertThat(appUsers).isNotEmpty();
	}
	
}
