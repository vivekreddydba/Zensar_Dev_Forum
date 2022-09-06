package com.zensar.df;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{ // To override default Authentication
		
		auth.userDetailsService(userDetailsService);
	}
	
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception { // to override default authorization
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/devforum/admin").hasRole("ADMIN")
		.antMatchers("/devforum/user").hasAnyRole("USER", "ADMIN")
		.antMatchers("/devforum/user/authenticate", "/devforum/user/logout").permitAll()
		.and()
		.formLogin();
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception{
		return super.authenticationManager();
	}
	 
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}


}
