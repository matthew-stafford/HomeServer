package com.github.matthewstafford.homeserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.matthewstafford.homeserver.business.ResourceUsage;

@RestController
public class ResourceUsageController {

	// static because we only want a single instance of this served for all clients
	private static ResourceUsage rs;

	@GetMapping("/api/resources")
	public ResourceUsage getResourceUsage() {
		if (rs == null) {
			rs = new ResourceUsage();
		}
		rs.update();
		return rs;
	}

}
