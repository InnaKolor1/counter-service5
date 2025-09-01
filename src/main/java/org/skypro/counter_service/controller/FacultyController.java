package org.skypro.counter_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/search")
    public ResponseEntity<List<Faculty>> searchByNameOrColor(@RequestParam String nameOrColor) {
        return ResponseEntity.ok(facultyService.findByNameOrColor(nameOrColor));
    }
}