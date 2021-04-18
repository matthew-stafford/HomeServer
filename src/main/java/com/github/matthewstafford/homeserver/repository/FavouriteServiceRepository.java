package com.github.matthewstafford.homeserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.matthewstafford.homeserver.entity.FavouriteService;

public interface FavouriteServiceRepository extends JpaRepository<FavouriteService, Integer> {
}
