package com.github.matthewstafford.homeserver.controllers;

import java.io.File;
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
		return fileLocationService.addFileLocation(body.get("fileLocation"));
	}
	
	@PostMapping("/api/files")
	private String[] listFiles(@RequestBody Map<String, Object> body) {
		String fileLocation = body.get("fileLocation").toString();
		
		System.out.println("fileLocation="+fileLocation);
		
		if (fileLocation != null && fileLocation.length() > 0) {
			// check prefix of path is in repo
			boolean approved = false;
			
			for (FileLocation location: fileLocationRepository.findAll()) {
				System.out.println("FileLocation="+fileLocation+", data="+location.getFileLocation());
				if (location.getFileLocation().equalsIgnoreCase(fileLocation)) {
					// ok
					approved = true;
					break;
				}
			}

			System.out.println("approved="+approved);
			if (approved) {
				File file = new File(fileLocation);
				return file.list();
			}
		}
		
		return null;
	}
	
}
