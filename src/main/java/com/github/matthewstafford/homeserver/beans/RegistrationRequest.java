package com.github.matthewstafford.homeserver.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RegistrationRequest {

	private String email;
	private String password;
		
}
