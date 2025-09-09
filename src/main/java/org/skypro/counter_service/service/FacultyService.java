package org.skypro.counter_service.service;

import org.springframework.stereotype.Service;

import org.skypro.counter_service.model.Faculty;
import org.skypro.counter_service.resources.FacultyRepository;
import java.util.List;



@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty create(Faculty faculty) {
        return facultyRepository.save(faculty);


    }

    public Faculty read(long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty update(Faculty faculty) {
        return facultyRepository.save(faculty);


    }

    public Faculty delete(long id) {
        Faculty faculty = read(id);
        if (faculty != null) {
            facultyRepository.deleteById(id);
        }
        return faculty;
    }

    public List<Faculty> getByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> getByName(String name) {
        return facultyRepository.findByName(name);
    }

    public List<Faculty> findByNameOrColor(String nameOrColor) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);
    }

    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }
}