package com.github.matthewstafford.homeserver.beans;

public class ServerServiceBean {

	private int port;
	private String favicon;
	private String name;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ServerServiceBean(int port) {

	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getFavicon() {
		return favicon;
	}

	public void setFavicon(String favicon) {
		this.favicon = favicon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ServerServiceBean [port=" + port + ", favicon=" + favicon + ", name=" + name + "]";
	}

}
