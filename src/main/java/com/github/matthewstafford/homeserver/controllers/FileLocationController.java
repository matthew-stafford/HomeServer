package com.github.matthewstafford.homeserver.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.matthewstafford.homeserver.business.FileLocationService;
import com.github.matthewstafford.homeserver.entity.FileLocation;
import com.github.matthewstafford.homeserver.repository.FileLocationRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class FileLocationController {

	@Autowired
	private FileLocationRepository fileLocationRepository;
	
	@Autowired
	private FileLocationService fileLocationService;
	
	@PostMapping("/api/saveFileLocation")
	private FileLocation addFileLocation(@RequestBody Map<String, String> body) {
		
		for (Map.Entry<String, String> entry : body.entrySet()) {
			System.out.println("key="+entry.getKey()+", value="+entry.getValue());
		}
		
		return fileLocationService.addFileLocation(body.get("fileLocation"));
	}
	
	
}
