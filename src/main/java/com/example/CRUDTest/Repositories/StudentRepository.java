package com.example.CRUDTest.Repositories;

import com.example.CRUDTest.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
