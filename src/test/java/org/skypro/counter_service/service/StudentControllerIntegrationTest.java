package org.skypro.counter_service.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.skypro.counter_service.model.Student;
import org.skypro.counter_service.service.StudentService;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.liquibase.enabled=false"})
class StudentControllerIntegrationTest {

    @Autowired
    private StudentService studentService;

    @Test
    void calculateSum_ShouldReturnCorrectValue() {
        Long result = studentService.calculateSum();
        assertEquals(500000500000L, result);
    }

    @Test
    void calculateSumOptimized_ShouldReturnSameValueAsSequential() {
        Long sequential = studentService.calculateSum();
        Long optimized = studentService.calculateSumOptimized();
        assertEquals(sequential, optimized);
    }

    @Test
    void getStudentNamesStartingWithA_ShouldReturnCorrectNames() {
        studentService.createStudent(new Student("Alice", 20));
        studentService.createStudent(new Student("Andrew", 22));
        studentService.createStudent(new Student("Anna", 21));
        studentService.createStudent(new Student("Bob", 23));

        var result = studentService.getStudentNamesStartingWithA();
        assertEquals(3, result.size());
        assertTrue(result.toString("ALICE"));
        assertTrue(result.toString("ANDREW"));
        assertTrue(result.toString("ANNA"));
    }
}