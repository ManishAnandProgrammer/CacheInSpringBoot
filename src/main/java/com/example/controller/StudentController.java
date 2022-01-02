package com.example.controller;

import com.example.domain.Student;
import com.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class StudentController {
    private final static Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.save(student);
            return ResponseEntity.created(URI.create("/students")).body(savedStudent);
        } catch (Exception exception) {
            LOGGER.error("Exception in Saving Student", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getById(@PathVariable final Long id) {
        try {
            Student student = studentService.findById(id);
            return ResponseEntity.ok(student);
        } catch (Exception exception) {
            LOGGER.error("Exception in Getting Student By Id:: {}", id, exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/students")
    public ResponseEntity<Student> update(@RequestBody Student student) {
        try {
            Student updatedStudent = studentService.update(student);
            return ResponseEntity.ok(updatedStudent);
        } catch (Exception exception) {
            LOGGER.error("Exception in updating student ", exception);
            return ResponseEntity.internalServerError().build();
        }
    }
}
