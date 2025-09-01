package org.skypro.counter_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.skypro.counter_service.model.Student;
import org.skypro.counter_service.service.StudentService;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/age-between")
    public ResponseEntity<List<Student>> getStudentsByAgeBetween(
            @RequestParam int minAge,
            @RequestParam int maxAge) {
        return ResponseEntity.ok(studentService.findByAgeBetween(minAge, maxAge));
    }

    @GetMapping("/by-faculty")
    public ResponseEntity<List<Student>> getStudentsByFaculty(@RequestParam Long facultyId) {
        return ResponseEntity.ok(studentService.findByFacultyId(facultyId));
    }
}