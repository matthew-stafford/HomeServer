package com.github.matthewstafford.homeserver.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.matthewstafford.homeserver.entity.ApplicationUser;
import com.github.matthewstafford.homeserver.repository.ApplicationUserRepository;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
	
	private final static String EMAIL_NOT_FOUND_MSG = "Email address: %s not found"; 
	private final static String EMAIL_IN_USE_MSG = "Email address: %s already in use"; 
	
	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) {

		return applicationUserRepository
				.findByUsername(username)
				.orElseThrow( () -> new UsernameNotFoundException( String.format(EMAIL_NOT_FOUND_MSG, username) ) );
		
	}

	public String registerInitialUser(ApplicationUser user) {
		boolean userExists = applicationUserRepository.findByUsername(user.getUsername()).isPresent();
		if (userExists) {
			throw new IllegalStateException( String.format(EMAIL_IN_USE_MSG, user.getUsername()) );
		}
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		applicationUserRepository.save(user);
		
		return "login";
	}

}
