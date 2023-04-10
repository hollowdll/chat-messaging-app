package com.example.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.app.user.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	
	private final UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	// Configure web security and secured endpoints
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests()
				.requestMatchers("/", "/login", "/signup").permitAll()
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/messagerooms", true)
				.permitAll()
				.and()
			.logout()
				.permitAll()
				.and()
			.httpBasic();
		return http.build();
	}
	
	// Configure BCrypt for user authentication
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder(12));
	}
	
}
