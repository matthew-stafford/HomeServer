package com.github.matthewstafford.homeserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.matthewstafford.homeserver.entity.CoinGeckoTicker;

public interface CoinGeckoTickerRepository extends JpaRepository<CoinGeckoTicker, Integer> {
}
