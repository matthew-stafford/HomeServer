package com.github.matthewstafford.homeserver.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.matthewstafford.homeserver.repository.ApplicationUserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/favicon.ico", "/css/**", "/js/**", "/built/**", "/login", "/initial-register", "/perform_login")
				.permitAll()			
			.antMatchers(HttpMethod.POST, "/initial-register", "/login", "/perform_login")
				.permitAll()
			.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/perform_login")
				.defaultSuccessUrl("/", true)
				.and()
				.rememberMe();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// if db doesnt exist, populate with default schema
	    if (!dataSource.getConnection().getMetaData().getTables(null, "", "USERS", null).first()) {
	        auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema();
	    }
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder)
			.and()
			.userDetailsService(userDetailsService);

	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	

}
