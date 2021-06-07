package com.github.matthewstafford.homeserver.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServerServiceBean {

	private int port;
	private String favicon;
	private String name;
	private String url;

	public ServerServiceBean(int port) {

	}

	@Override
	public String toString() {
		return "ServerServiceBean [port=" + port + ", favicon=" + favicon + ", name=" + name + "]";
	}

}
