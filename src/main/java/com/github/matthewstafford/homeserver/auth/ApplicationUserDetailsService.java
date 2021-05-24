package com.github.matthewstafford.homeserver.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ApplicationUserDao applicationUserDao;

	@Override
	public UserDetails loadUserByUsername(String username) {

		return applicationUserDao
				.selectUserByUsername(username)
				.orElseThrow( () -> new UsernameNotFoundException(username +" not found") );
		
	}
	


}
