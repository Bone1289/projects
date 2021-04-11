package com.bone.sprinbatch.config;

import com.bone.sprinbatch.model.User;
import com.bone.sprinbatch.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<User> {

	private final UserRepository userRepository;

	public DBWriter(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void write(List<? extends User> users) throws Exception {
		System.out.println("Data Saved for Users: " + users);
		userRepository.saveAll(users);
	}
}
