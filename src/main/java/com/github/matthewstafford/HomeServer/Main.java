package com.github.matthewstafford.HomeServer;

import java.util.Map.Entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.matthewstafford.HomeServer.services.ServerServiceBean;
import com.github.matthewstafford.HomeServer.services.ServerServiceDetector;

@SpringBootApplication
public class Main {

	/*
	 * Server port can be changed by using -Dserver.port=80 as a param when
	 * running the jar file
	 */
	public static void main(String[] args) {
		System.out.println("Starting ssd");
		long start = System.currentTimeMillis();
		ServerServiceDetector ssd = new ServerServiceDetector();
		ssd.setPorts();
		for (Entry<Integer, ServerServiceBean> port : ssd.getPorts().entrySet()) {
			System.out.println("port=" + port.getKey());
		}
		System.out.println("Stopped in " + (System.currentTimeMillis() - start) + "ms");

		SpringApplication.run(Main.class, args);

	}

}