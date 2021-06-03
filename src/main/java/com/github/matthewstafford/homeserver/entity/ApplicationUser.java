package com.github.matthewstafford.homeserver.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.github.matthewstafford.homeserver.auth.ApplicationUserRole;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class ApplicationUser implements UserDetails {
	
	@Id
	@SequenceGenerator(
			name="id_sequence",
			sequenceName="id_sequence",
			allocationSize=1
	)
	
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator="id_sequence"
	)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ApplicationUserRole applicationUserRole;
		
	private String password, username;
	
	private boolean isAccountNonExpired = false, isAccountNonLocked = false, isCredentialsNonExpired = false, isEnabled = false;
	 
	
	public ApplicationUser(String password, String username, ApplicationUserRole applicationUserRole, boolean isAccountNonExpired, boolean isAccountNonLocked,
			boolean isCredentialsNonExpired, boolean isEnabled) {
		this.password = password;
		this.username = username;
		this.applicationUserRole = applicationUserRole;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority  sga = new SimpleGrantedAuthority(applicationUserRole.name());
		return Collections.singletonList(sga);
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}

	
	
}
