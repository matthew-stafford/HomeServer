package com.github.matthewstafford.homeserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.matthewstafford.homeserver.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User getByUsername(String username);
	
	int getUserCount();

}
