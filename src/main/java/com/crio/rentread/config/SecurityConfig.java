package com.crio.rentread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		//.csrf().disable().
		.authorizeHttpRequests(authorizeRequests -> 
			authorizeRequests
			.requestMatchers("/registration", "/login").permitAll()
			.requestMatchers("/user/**").hasRole("USER")
			.anyRequest().authenticated())
		.formLogin(formLogin ->
        formLogin.disable()
            //.loginPage("/login") // Custom login page, if you have one
            //.permitAll() // Allow access to the login page
    )
    .logout(logout ->
        logout.permitAll() // Allow access to logout functionality
    ).csrf(csrf -> csrf.disable());
//		.authorizeRequests()
//		.requestMatchers("/registration").permitAll()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic()
//		.and()
//		.csrf()
//		.disable();
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(User.withUsername("user1").password(passwordEncoder().encode("password1")).roles("USER").build());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
