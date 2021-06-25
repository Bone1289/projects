package com.bone.spring.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class User implements UserDetails {
	private String username;
	private String password;

	@Override public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton((GrantedAuthority) () -> "read");
	}

	@Override public String getPassword() {
		return password;
	}

	@Override public String getUsername() {
		return username;
	}

	@Override public boolean isAccountNonExpired() {
		return true;
	}

	@Override public boolean isAccountNonLocked() {
		return true;
	}

	@Override public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override public boolean isEnabled() {
		return true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
