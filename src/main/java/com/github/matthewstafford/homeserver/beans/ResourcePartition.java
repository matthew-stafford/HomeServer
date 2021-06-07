package com.github.matthewstafford.homeserver.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResourcePartition {
	private String name;
	private long size, freeSpace;

}
