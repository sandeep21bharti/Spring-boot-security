package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AuthRequest;
import com.example.demo.util.jwtUtil;

@RestController
public class WelcomeController {
	
	@Autowired
	private jwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/abc")
	public String welcome() {
		
		return "Welcome to Json web token practice";
	}

	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authrequest) throws Exception {
       try {
			System.out.println("Inside authenticate method");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));
		}catch(Exception ex) {
			 throw new Exception("Inavlid username and password Exception.");
		}
		
		return jwtUtil.generateToken(authrequest.getUsername());
		
	}
}
