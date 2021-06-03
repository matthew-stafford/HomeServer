package com.github.matthewstafford.homeserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.matthewstafford.homeserver.entity.ApplicationUser;

@Repository
@Transactional(readOnly = true)
public interface ApplicationUserRepository {

	Optional<ApplicationUser> findByEmail(String email);
	int getUserCount();
	
}
