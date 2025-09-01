package org.skypro.counter_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.skypro.counter_service.model.Student;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeBetween(int minAge, int maxAge);
    List<Student> findByFacultyId(Long facultyId);
}