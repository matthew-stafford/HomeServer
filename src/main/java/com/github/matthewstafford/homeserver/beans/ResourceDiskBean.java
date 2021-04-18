package com.github.matthewstafford.homeserver.beans;

import java.util.HashMap;

public class ResourceDiskBean {
	private String name;
	private long size, freeSpace;
	private HashMap<String, ResourcePartition> partitions;

	public ResourceDiskBean(String name) {
		this.name = name;

		this.partitions = new HashMap<String, ResourcePartition>();
	}

	public HashMap<String, ResourcePartition> getPartitions() {
		return partitions;
	}

	public void setPartitions(HashMap<String, ResourcePartition> partitions) {
		this.partitions = partitions;
	}

	public long getFreeSpace() {
		return freeSpace;
	}

	public void setFreeSpace(long freeSpace) {
		this.freeSpace = freeSpace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
