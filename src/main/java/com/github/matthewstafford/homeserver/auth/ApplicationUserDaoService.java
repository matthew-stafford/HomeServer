package com.github.matthewstafford.homeserver.auth;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationUserDaoService implements ApplicationUserDao {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public Optional<ApplicationUser> selectUserByUsername(String username) {
		return Optional.empty();
	}

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

}
