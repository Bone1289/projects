package com.bone.sprinbatch.config;

import com.bone.sprinbatch.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<User, User> {
	private static final Map<String, String> DEPT_NAMES = new HashMap<>();

	static {
		DEPT_NAMES.put("001", "Tech");
		DEPT_NAMES.put("002", "IT");
		DEPT_NAMES.put("003", "Accounts");
	}

	@Override
	public User process(User user) throws Exception {
		String deptCode = user.getDept();
		String dept = DEPT_NAMES.getOrDefault(deptCode, "Others");
		user.setDept(dept);
		user.setTime(new Date());
		System.out.println(String.format("Converted from [%s] to [%s]", deptCode, dept));

		return user;
	}
}
