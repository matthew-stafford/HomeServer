package com.github.matthewstafford.homeserver.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.matthewstafford.homeserver.beans.RegistrationRequest;
import com.github.matthewstafford.homeserver.business.RegistrationService;
import com.github.matthewstafford.homeserver.repository.ApplicationUserRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RegisterController {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping("/initial-register") 
	private String register(@RequestParam Map<String, String> body) {
		if (applicationUserRepository.count() == 0) {
			return registrationService.register(new RegistrationRequest(body.get("email"), body.get("password")));
		} else {
			throw new IllegalStateException("Initial setup already complete");
		}
	}
	
}
