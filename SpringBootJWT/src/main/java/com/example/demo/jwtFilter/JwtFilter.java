package com.example.demo.jwtFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.services.CustomUserDetailService;
import com.example.demo.util.jwtUtil;

@Component
public class JwtFilter  extends OncePerRequestFilter{
    
	@Autowired
	private jwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("filter chain list"+filterChain.getClass());
		//Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5b2dlbmRyYSIsImV4cCI6MTYxMzk0OTA3NiwiaWF0IjoxNjEzOTEzMDc2fQ.Phk3o3Ly8dKY-N-IVP-kCtZsLnf5gPJpG6KU-jJ8ACA
		String authorizationHeader= request.getHeader("Authorization");
		String token =null;
		String username=null;
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer")) {
			 token =authorizationHeader.substring(7);
			//extract username
			 username=jwtUtil.extractUsername(token);
		}
			 
			 
			 if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				 UserDetails userDetails= customUserDetailService.loadUserByUsername(username);
				 System.out.println(userDetails.getPassword()+"username"+userDetails.getUsername());
				 if (jwtUtil.validateToken(token, userDetails)) {

						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				 
			 }
			 filterChain.doFilter(request, response);
		
		
		
	}

}
