package com.springBlog;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springBlog.config.CustomUserDetails;
import com.springBlog.entities.Role;
import com.springBlog.entities.User;
import com.springBlog.repositories.UserRepository;
import com.springBlog.service.UserService;

@SpringBootApplication
public class SpringBlogApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBlogApplication.class, args);
	}
	
	/**
	 * Password grants are switched on by injecting an AuthenticationManager.
	 * Here, we setup the builder so that the userDetailsService is the one we coded.
	 * @param builder
	 * @param repository
	 * @throws Exception
	 */
/*	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService userService) throws Exception {
		if (repository.count()==0)
			userService.save(new User("admin", "adminPassword", Arrays.asList(new Role("USER"), new Role("ACTUATOR") , new Role("ADMIN"))));
		System.out.println("1.1");
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
		System.out.println("1.2");
		System.out.println(this.passwordEncoder.encode("adminPassword"));
	}*/

	/**
	 * We return an istance of our CustomUserDetails.
	 * @param repository
	 * @return
	 */
	/*private UserDetailsService userDetailsService(final UserRepository repository) {
		UserDetailsService userDetailsService;
		System.out.println("1.3");
		userDetailsService = username -> new CustomUserDetails(repository.findByUsername(username));
		System.out.println("1.4");
		return userDetailsService;
	}*/
}
