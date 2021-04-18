package com.github.matthewstafford.homeserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.matthewstafford.homeserver.Main;
import com.github.matthewstafford.homeserver.business.AutomatedServerServices;

/**
 * class takes up to 15 seconds for detection so only run it once at boot or
 * unless requested by user
 * 
 * @author matt
 *
 */

@RestController
public class AutomatedServiceController {

	@GetMapping("/api/services")
	public AutomatedServerServices getServices() {
		return new AutomatedServerServices();
	}

	@PutMapping("/api/services")
	public void updateServices() {
		Main.ssd.update();
	}
}
