package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private  PasswordEncoder passwordEncoder;
	
@Override
protected void configure(HttpSecurity http) throws Exception {
	http
	.authorizeRequests()
	//below matcher will whitelist url with / and permit only this just to skip basic authentication
	//and later if endpoint is called then has to be authenticated
	//for this added static thing in src/main/resource/index.html
	.antMatchers("/")
	.permitAll()// it permits only for above specified pattern requests, rest other authenticate
	.anyRequest()
	.authenticated()
	.and()
	.httpBasic();

}

@Override
@Bean
protected UserDetailsService userDetailsService() {
	
	UserDetails annaSmithUser=User.builder()
			.username("annasmith")
			.password(passwordEncoder.encode("password"))
			.roles("STUDENT")
			.build();
	return new InMemoryUserDetailsManager(annaSmithUser);
} 


}
