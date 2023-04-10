package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.user.AppUserDAO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ChatAppApplicationTests {

	@Autowired
	private AppUserDAO appUserDao;
	
	@Test
	public void appUserDAOLoads() {
		assertThat(appUserDao).isNotNull();
	}

}
