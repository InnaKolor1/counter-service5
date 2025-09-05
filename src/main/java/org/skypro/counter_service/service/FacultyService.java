package org.skypro.counter_service.service;

import org.springframework.stereotype.Service;

import org.skypro.counter_service.model.Faculty;
import org.skypro.counter_service.repository.FacultyRepository;
import java.util.List;


@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> findByName(String name) {
        return facultyRepository.findByNameIgnoreCase(name);
    }

    public List<Faculty> findByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }

    public List<Faculty> findByNameOrColor(String name) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, name);
    }

    public List<Faculty> searchByName(String part) {
        return facultyRepository.findByNameContainingIgnoreCase(part);
    }

    public List<Faculty> searchByNameOrColor(String part) {
        return facultyRepository.findByNameContainingIgnoreCaseOrColorContainingIgnoreCase(part, part);
    }
}