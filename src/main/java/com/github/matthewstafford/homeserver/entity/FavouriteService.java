package com.github.matthewstafford.homeserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "favourite_services")
@Data
public class FavouriteService {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@Column(name = "url", unique = true, nullable = false)
	private String url;

	@Column(name = "name", unique = true, nullable = false)
	private String name;
}
