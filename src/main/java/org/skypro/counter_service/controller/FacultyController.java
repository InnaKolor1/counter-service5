package org.skypro.counter_service.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.skypro.counter_service.model.Faculty;
import org.skypro.counter_service.service.FacultyService;
import java.util.List;


@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }

    @PutMapping("/{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        return facultyService.updateFaculty(id, faculty);
    }

    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }

    @GetMapping("/color/{color}")
    public List<Faculty> getFacultiesByColor(@PathVariable String color) {
        return facultyService.getFacultiesByColor(color);
    }

    @GetMapping("/search")
    public List<Faculty> searchFaculties(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String color) {
        return facultyService.searchFaculties(name, color);
    }

    @GetMapping
    public List<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("/longest-name")
    public String getLongestFacultyName() {
        return facultyService.getLongestFacultyName();
    }
}