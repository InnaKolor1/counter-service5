package org.skypro.counter_service.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.skypro.counter_service.model.Student;
import org.skypro.counter_service.service.StudentService;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class StudentControllerIntegrationTest {

    @Autowired
    private StudentService studentService;

    @Test
    void calculateSumOptimized_ShouldReturnCorrectValue() {
        long result = studentService.calculateSumOptimized();
        long expected = 500000500000L;
        assertEquals(expected, result);
    }
}