package com.udemy.microservice.user.repository;

import com.udemy.microservice.user.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
