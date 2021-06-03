package com.github.matthewstafford.homeserver.auth;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.matthewstafford.homeserver.entity.ApplicationUser;
import com.github.matthewstafford.homeserver.repository.ApplicationUserRepository;

@Repository
public class ApplicationUserService implements ApplicationUserRepository {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public int getUserCount() {
		try {
			Statement stmt = dataSource.getConnection().createStatement();
			stmt.execute("SELECT COUNT(*) AS UserCount FROM USERS");
			ResultSet result = stmt.getResultSet();
			
			if (result.first() == false)
				return 0;
			
			else 
				return result.getInt("UserCount");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public Optional<ApplicationUser> findByEmail(String email) {
		System.out.println("find by email");
		return null;
	}


}
