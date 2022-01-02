package com.example.service;

import com.example.domain.Student;
import com.example.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final static Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @CacheEvict(value = "studentsWithFirstName", allEntries = true)
    public Student save(Student student) {
        LOGGER.info("Going to Save Student with details {}", student);
        return studentRepository.save(student);
    }

    @Cacheable(value = "student", key = "#id")
    public Student findById(final Long id) {
        LOGGER.info("Going to fetch Student With Id {} ", id);
        return studentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("No Record Found From Given Id " + id)
        );
    }

    @CachePut(value = "student", key = "#student.id")
    @CacheEvict(value = "studentsWithFirstName", allEntries = true)
    public Student update(Student student) {
        LOGGER.info("Going to update Student {}", student);
        return studentRepository.save(student);
    }

    @Cacheable(value = "studentsWithFirstName", key = "#firstName")
    public List<Student> findByFirstName(final String firstName) {
        LOGGER.info("Going to fetch student with firstName {}", firstName);
        return studentRepository.findByFirstNameIgnoreCase(firstName);
    }
}
