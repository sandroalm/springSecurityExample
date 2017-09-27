package com.forFun;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.forFun.api.dto.CustomUserDetails;
import com.forFun.api.service.UserService;
import com.forFun.model.User;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	// this is just a example of creating users for learning purposes.
	// this part should never run into production!
	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... arg0) throws Exception {

		User admin = new User();
		admin.setUserid(1l);
		admin.setUserName("admin");
		admin.setPassword("admin");
		admin.setEnabled(true);

		List<String> userRoles = Arrays.asList("ROLE_ADMIN");
		CustomUserDetails custonUser = new CustomUserDetails(admin, userRoles, true);
		userService.createByUserDetails(custonUser);

		User user = new User();
		user.setUserid(2l);
		user.setUserName("user");
		user.setPassword("user");
		user.setEnabled(true);
		userService.create(user);
		
	}
	
}
