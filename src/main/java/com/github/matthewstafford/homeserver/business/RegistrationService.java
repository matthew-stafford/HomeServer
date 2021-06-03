package com.github.matthewstafford.homeserver.business;

import org.springframework.stereotype.Service;

import com.github.matthewstafford.homeserver.beans.RegistrationRequest;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class RegistrationService {

	public String register(RegistrationRequest request) {
		return "it works";
	}

}
