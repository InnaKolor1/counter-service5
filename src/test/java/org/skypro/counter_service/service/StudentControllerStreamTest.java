package org.skypro.counter_service.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.skypro.counter_service.controller.StudentController;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
@SpringBootTest
class StudentControllerStreamTest {

    @Autowired
    private StudentService studentService;

    @Test
    void testCalculateSum() {
        long result = studentService.calculateSum();
        long expected = 500000500000L;
        assertEquals(expected, result);
    }

    @Test
    void testCalculateSumOptimized() {
        long result = studentService.calculateSumOptimized();
        long expected = 500000500000L;
        assertEquals(expected, result);
    }
}