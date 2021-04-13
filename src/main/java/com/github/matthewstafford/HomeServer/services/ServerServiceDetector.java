package com.github.matthewstafford.HomeServer.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ServerServiceDetector {

	public TreeMap<Integer, ServerServiceBean> ports = new TreeMap<Integer, ServerServiceBean>();

	public void setPorts() {
		final int min = 1, max = 65535;
		// ports below 1024 are not allowed to be bound by a user on Linux 
		for (int i = min; i < max; i++) {
			ServerServiceBean bean = checkWebService("http://192.168.1.240", i);
			if (bean != null) {
				ports.put(i, bean);
			}
		}
	}

	public TreeMap<Integer, ServerServiceBean> getPorts() {
		return ports;
	}

	private ServerServiceBean checkWebService(String url, int port) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url + ":" + port).openConnection();
			connection.setReadTimeout(300);
			connection.setConnectTimeout(300);
			connection.setRequestMethod("HEAD");

			int responseCode = connection.getResponseCode();

			// is a web service, create bean for it
			if (responseCode > -1) {
				System.out.println("gen service for " + url);
				return generateServerServiceBean(url, port);
			} else {
				// open port but not a web service
				return null;
			}
		} catch (IOException exception) {
			return null;
		}
	}

	private ServerServiceBean generateServerServiceBean(String url, int port) {
		ServerServiceBean sb = new ServerServiceBean(port);

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url + ":" + port).openConnection();
			connection.setReadTimeout(1000);
			connection.setConnectTimeout(1000);
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();

			System.out.println("Generate Server Bean: " + responseCode);
			// check body for title and favicon

			Document doc = Jsoup.connect(url + ":" + port).get();
			System.out.println("title=" + doc.title());

		} catch (IOException exception) {
			return null;
		}

		return sb;
	}
}
