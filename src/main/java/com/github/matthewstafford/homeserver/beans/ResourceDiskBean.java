package com.github.matthewstafford.homeserver.beans;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceDiskBean {
	private String name;
	private long size, freeSpace;
	private HashMap<String, ResourcePartition> partitions;

	public ResourceDiskBean(String name) {
		this.name = name;

		this.partitions = new HashMap<String, ResourcePartition>();
	}

}
