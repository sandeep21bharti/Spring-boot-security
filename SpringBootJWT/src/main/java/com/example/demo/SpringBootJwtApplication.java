package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

//from stack Instance site youtube.

@SpringBootApplication
public class SpringBootJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtApplication.class, args);
		
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void initUser() {
	List<User>userList=Stream.of(
			new User( "yogendra", "yogendra", "yogendra@gmail.com"),
			new User( "swati", "swati", "swati@gmail.com")).collect(Collectors.toList());
	userRepository.saveAll(userList);
	
	}

}
