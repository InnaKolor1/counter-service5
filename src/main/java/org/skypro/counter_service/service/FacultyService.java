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

    public List<Faculty> findByNameOrColor(String nameOrColor) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);
    }

    public Object findByName(String s) {

    }

    public Object findByColor(String s) {
    }
}
