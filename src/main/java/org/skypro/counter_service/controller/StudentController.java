package org.skypro.counter_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/age/{age}")
    public List<Student> getStudentsByAge(@PathVariable int age) {
        return studentService.getStudentsByAge(age);
    }

    @GetMapping("/age/between")
    public List<Student> getStudentsByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.getStudentsByAgeBetween(min, max);
    }

    @GetMapping("/faculty/{facultyId}")
    public List<Student> getStudentsByFaculty(@PathVariable Long facultyId) {
        return studentService.getStudentsByFaculty(facultyId);
    }

    @GetMapping("/count")
    public Integer getStudentsCount() {
        return studentService.getStudentsCount();
    }

    @GetMapping("/average-age")
    public Double getStudentsAverageAge() {
        return studentService.getStudentsAverageAge();
    }

    @GetMapping("/names-starting-with-a")
    public List<String> getStudentNamesStartingWithA() {
        return studentService.getStudentNamesStartingWithA();
    }

    @GetMapping("/stream/sum")
    public Long calculateSum() {
        return studentService.calculateSum();
    }

    @GetMapping("/stream/sum-optimized")
    public Long calculateSumOptimized() {
        return studentService.calculateSumOptimized();
    }
}