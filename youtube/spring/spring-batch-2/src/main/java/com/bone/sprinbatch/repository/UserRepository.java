package com.bone.sprinbatch.repository;

import com.bone.sprinbatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
