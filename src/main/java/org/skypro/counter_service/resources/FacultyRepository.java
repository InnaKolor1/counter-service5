package org.skypro.counter_service.resources;

import org.springframework.data.jpa.repository.JpaRepository;

import org.skypro.counter_service.model.Faculty;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);

    List<Faculty> findByColorIgnoreCase(String red);

    List<Faculty> findByNameIgnoreCase(String gryffindor);

    List<Faculty> findByName(String name);

    List<Faculty> findByColor(String color);

    List<Faculty> findByNameContainingIgnoreCase(String part);

    List<Faculty> findByNameContainingIgnoreCaseOrColorContainingIgnoreCase(String part, String part1);
}