package com.vaccine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaccine.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentId(String studentId);

    List<Student> findByNameContainingIgnoreCase(String name);
}
