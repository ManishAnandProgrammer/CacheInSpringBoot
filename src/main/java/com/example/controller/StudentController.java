package com.example.controller;

import com.example.domain.Student;
import com.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/students/id/{id}")
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

    @GetMapping("/students/first/{name}")
    public ResponseEntity<List<Student>> getByFirstName(@PathVariable final String name) {
        try {
            List<Student> student = studentService.findByFirstName(name);
            return ResponseEntity.ok(student);
        } catch (Exception exception) {
            LOGGER.error("Exception in Getting Students By Name:: {}", name, exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/students/middle/{name}")
    public ResponseEntity<List<Student>> getByMiddleName(@PathVariable final String name) {
        try {
            List<Student> student = studentService.findByMiddleName(name);
            return ResponseEntity.ok(student);
        } catch (Exception exception) {
            LOGGER.error("Exception in Getting Students By Name:: {}", name, exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/students/last/{name}")
    public ResponseEntity<List<Student>> getByLastName(@PathVariable final String name) {
        try {
            List<Student> student = studentService.findByLastName(name);
            return ResponseEntity.ok(student);
        } catch (Exception exception) {
            LOGGER.error("Exception in Getting Students By Name:: {}", name, exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long id) {
        try {
            studentService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            LOGGER.error("Exception in Deleting Student with Id {}", id, exception);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
