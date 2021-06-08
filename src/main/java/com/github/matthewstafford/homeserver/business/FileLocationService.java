package com.github.matthewstafford.homeserver.business;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.matthewstafford.homeserver.entity.FileLocation;
import com.github.matthewstafford.homeserver.repository.FileLocationRepository;

@Service
public class FileLocationService {

	@Autowired
	private FileLocationRepository fileLocationRepository;
	
	public FileLocation addFileLocation(String fileLocation) {
		File f = new File(fileLocation);
		FileLocation fl = new FileLocation();
		
		// set file location only if found and it is a folder not a file
		if (f.exists() && f.isDirectory()) {		
			fl.setFileLocation(fileLocation);
			fileLocationRepository.save(fl);
		}

		// return null, if not found
		return fl;
	}
	
}
