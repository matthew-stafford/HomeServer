package com.github.matthewstafford.homeserver.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.matthewstafford.homeserver.repository.ApplicationUserRepository;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
	
	private final static String EMAIL_NOT_FOUND_MSG = "Email address: %s not found"; 
	
	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {

		return applicationUserRepository
				.findByEmail(email)
				.orElseThrow( () -> new UsernameNotFoundException( String.format(EMAIL_NOT_FOUND_MSG, email) ) );
		
	}
	


}
