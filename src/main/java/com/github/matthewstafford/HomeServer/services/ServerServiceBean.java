package com.github.matthewstafford.HomeServer.services;

public class ServerServiceBean {

	private int port;
	private String url;
	private String name;

	public ServerServiceBean(int port) {

	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
