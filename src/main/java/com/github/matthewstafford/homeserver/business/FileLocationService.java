package com.github.matthewstafford.homeserver.business;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.matthewstafford.homeserver.auth.ApplicationUserRole;
import com.github.matthewstafford.homeserver.beans.RegistrationRequest;
import com.github.matthewstafford.homeserver.entity.ApplicationUser;
import com.github.matthewstafford.homeserver.entity.FileLocation;
import com.github.matthewstafford.homeserver.repository.FileLocationRepository;

@Service
public class FileLocationService {

	@Autowired
	private FileLocationRepository fileLocationRepository;
	
	public FileLocation addFileLocation(String fileLocation) {
		System.out.println("fileLocation="+fileLocation);
		File f = new File(fileLocation);
		FileLocation fl = new FileLocation();
		if (f.exists()) {		
			fl.setFileLocation(fileLocation);
			fileLocationRepository.save(fl);
		}
		return fl;
	}
	
}
