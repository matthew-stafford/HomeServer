package com.github.matthewstafford.homeserver.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.matthewstafford.homeserver.auth.ApplicationUserDetailsService;
import com.github.matthewstafford.homeserver.auth.ApplicationUserRole;
import com.github.matthewstafford.homeserver.beans.RegistrationRequest;
import com.github.matthewstafford.homeserver.entity.ApplicationUser;
import com.github.matthewstafford.homeserver.repository.ApplicationUserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistrationService {

	@Autowired
	private ApplicationUserDetailsService applicationUserDetailsService;
	
	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	
	public String register(RegistrationRequest request) {
		
		// todo: validation of email/pw

		// if first user, set to admin, else standard		
		ApplicationUserRole role = applicationUserRepository.count() == 0 ? ApplicationUserRole.ADMIN : ApplicationUserRole.STANDARD;

		return applicationUserDetailsService.registerInitialUser(
					new ApplicationUser(
							request.getEmail(),
							request.getPassword(),
							role,
							true,true,true,true
					)
				
				);
				

	}

}
