package com.bone.service;

import com.bone.model.SecurityUser;
import com.bone.model.User;
import com.bone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class JPAUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> usersByUsername = userRepository.findUsersByUsername(username);

		User u = usersByUsername.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return new SecurityUser(u);
	}
}
