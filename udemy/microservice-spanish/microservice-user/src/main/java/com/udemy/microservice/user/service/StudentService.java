package com.udemy.microservice.user.service;

import com.udemy.microservice.user.entity.Student;

import java.util.Optional;

public interface StudentService {

    Iterable<Student> findAll();

    Optional<Student> findById(Long id);

    Student save(Student student);

    void deleteById(Long id);

}
