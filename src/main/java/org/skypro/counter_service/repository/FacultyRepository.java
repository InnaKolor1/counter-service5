package org.skypro.counter_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.skypro.counter_service.model.Faculty;
import java.util.List;
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByNameContainingIgnoreCase(String name);
    List<Faculty> findByColorContainingIgnoreCase(String color);
    List<Faculty> findByNameContainingIgnoreCaseAndColorContainingIgnoreCase(String name, String color);
}