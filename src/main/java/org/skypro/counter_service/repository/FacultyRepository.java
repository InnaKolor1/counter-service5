package org.skypro.counter_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.skypro.counter_service.model.Faculty;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);

    List<Faculty> findByColorIgnoreCase(String red);

    List<Faculty> findByNameIgnoreCase(String gryffindor);
}