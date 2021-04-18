package com.github.matthewstafford.homeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.matthewstafford.homeserver.business.ServerServiceDetector;

@SpringBootApplication
public class Main {

	public static ServerServiceDetector ssd;

	/*
	 * Server port can be changed by using -Dserver.port=80 as a param when
	 * running the jar file
	 */
	public static void main(String[] args) {

		// perform automated detection at boot, can also be run by user if service was started after this
		System.out.println("Performing automated web service detection");
		ssd = new ServerServiceDetector();
		ssd.update();
		System.out.println("Found " + ssd.getPorts().size() + " services");

		SpringApplication.run(Main.class, args);
	}

}