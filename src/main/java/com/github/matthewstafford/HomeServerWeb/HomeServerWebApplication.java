package com.github.matthewstafford.HomeServerWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeServerWebApplication {

	/*
	 * Server port can be changed by using -Dserver.port=80 as a param when
	 * running the jar file
	 */
	public static void main(String[] args) {
		SpringApplication.run(HomeServerWebApplication.class, args);
	}

}