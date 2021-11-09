package com.example.jwt;

import com.example.jwt.entities.Role;
import com.example.jwt.entities.User;
import com.example.jwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null,"serkan","gezginshaman","1234",new ArrayList<>()));
			userService.saveUser(new User(null,"john","jonny","1234",new ArrayList<>()));
			userService.saveUser(new User(null,"sarah","sarah","1234",new ArrayList<>()));

			userService.addRoleToUser("gezginshaman","ROLE_SUPER_ADMIN");
			userService.addRoleToUser("gezginshaman","ROLE_ADMIN");
			userService.addRoleToUser("jonny","ROLE_USER");
			userService.addRoleToUser("sarah","ROLE_USER");
		};
	}

}
