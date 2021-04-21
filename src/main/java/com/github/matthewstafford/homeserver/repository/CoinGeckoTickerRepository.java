package com.github.matthewstafford.homeserver.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.matthewstafford.homeserver.entity.CoinGeckoTicker;

public interface CoinGeckoTickerRepository extends CrudRepository<CoinGeckoTicker, Integer> {
}
