package com.github.matthewstafford.homeserver.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.matthewstafford.homeserver.repository.ApplicationUserRepository;

@Controller
public class LoginController {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	
	@GetMapping("/login")
	private String login() {
		if (applicationUserRepository.count() == 0) {
			return "initial-register";
		}
		return "login";
	}
}
