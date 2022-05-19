package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
/**
 * In this branch we are adding only spring security dependency then it 
 * automatically takes us to login page when trying to acces the endpoint
 *  so need to give automatically generated password printed on console and default user wil be : user
 *  click on page right and select inspect element and then go to network and the see how the requests
 *  work: login and logout post endpoints are automatically written by security so we can login and logout 
 *  and see things
 * @param args
 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
