package com.udemy.microservice.user.controllers;

import com.udemy.microservice.user.entity.Student;
import com.udemy.microservice.user.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok().body(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Student> studentDb = studentService.findById(id);
        if (studentDb.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(studentDb.get());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Student student) {
        Student studentDb = studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Student student, @PathVariable Long id) {
        Optional<Student> studentDb = studentService.findById(id);
        if (studentDb.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Student editedStudent = studentDb.get();
        editedStudent.setEmail(student.getEmail());
        editedStudent.setFirstName(student.getFirstName());
        editedStudent.setLastName(student.getLastName());

        return ResponseEntity.ok().body(studentService.save(editedStudent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
