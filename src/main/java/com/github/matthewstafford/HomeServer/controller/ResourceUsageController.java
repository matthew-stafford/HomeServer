package com.github.matthewstafford.HomeServer.controller;

import com.github.matthewstafford.HomeServer.resourceUsage.ResourceUsage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceUsageController {

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
