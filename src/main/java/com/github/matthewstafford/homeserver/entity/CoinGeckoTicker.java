package com.github.matthewstafford.homeserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table(name = "coin_gecko_tickers")
@Data
public class CoinGeckoTicker {

	@Id
	@NotNull
	@Column(name = "id", unique = true)
	private String id;

	@NotNull
	@Column(name = "name")
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CoinGeckoTicker() {
	}

}
