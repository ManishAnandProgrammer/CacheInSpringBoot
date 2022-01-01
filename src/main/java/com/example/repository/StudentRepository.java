package com.example.repository;

import com.example.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstNameIgnoreCase(@NonNull String firstName);

    List<Student> findByLastNameIgnoreCase(@NonNull String lastName);

    List<Student> findByMiddleNameIgnoreCase(@NonNull String middleName);

    long countByFirstNameIgnoreCase(@NonNull String firstName);

    long countByMiddleNameIgnoreCase(@NonNull String middleName);

    long countByLastNameIgnoreCase(@NonNull String lastName);

    boolean existsByFirstNameIgnoreCase(@NonNull String firstName);

}
