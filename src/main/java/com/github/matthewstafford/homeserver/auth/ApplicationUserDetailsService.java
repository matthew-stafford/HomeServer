package com.github.matthewstafford.homeserver.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.matthewstafford.homeserver.entity.User;
import com.github.matthewstafford.homeserver.repository.UserRepository;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
	
	@Autowired
	private final ApplicationUserDao applicationUserDao = null;

	@Override
	public UserDetails loadUserByUsername(String username) {

		return applicationUserDao
				.selectUserByUsername(username)
				.orElseThrow( () -> new UsernameNotFoundException(username +" not found") );
		
	}
	
	
	
	

}
