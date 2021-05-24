package com.github.matthewstafford.homeserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.matthewstafford.homeserver.auth.ApplicationUserDaoService;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private ApplicationUserDaoService applicationUserDaoService;
	
	@GetMapping("login")
	public String getLoginView() {
		if (applicationUserDaoService.getUserCount() == 0) {
			return "initial";
		}
		return "login";
	}
	
}
