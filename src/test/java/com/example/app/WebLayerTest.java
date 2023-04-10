package com.example.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {
	
	private final MockMvc mockMvc;
	private final String RESPONSE_CONTENT_TYPE = "text/html;charset=UTF-8";
	
	@Autowired
	public WebLayerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
	
	// root "/" GET request
	@Test
	public void rootShouldReturnView() throws Exception {
		this.mockMvc
			.perform(get("/"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(RESPONSE_CONTENT_TYPE));
	}
	
	// login GET request
	@Test
	public void loginShouldReturnView() throws Exception {
		this.mockMvc
			.perform(get("/login"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(RESPONSE_CONTENT_TYPE));
	}
	
	// signup GET request
	@Test
	public void signupShouldReturnView() throws Exception {
		this.mockMvc
			.perform(get("/signup"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(RESPONSE_CONTENT_TYPE));
	}
	
}
