package org.skypro.counter_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.skypro.counter_service.controller.FacultyController;
import org.skypro.counter_service.model.Faculty;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getStudentNamesStartingWithA_ShouldReturnNames() throws Exception {
        List<String> names = Arrays.asList("ALICE", "ANDREW", "ANNA");
        when(studentService.getStudentNamesStartingWithA()).thenReturn(names);

        mockMvc.perform(get("/student/names-starting-with-a"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0]").value("ALICE"))
                .andExpect(jsonPath("$[1]").value("ANDREW"))
                .andExpect(jsonPath("$[2]").value("ANNA"));
    }

    @Test
    void getStudentsAverageAge_ShouldReturnAverage() throws Exception {
        when(studentService.getStudentsAverageAge()).thenReturn(20.5);

        mockMvc.perform(get("/student/average-age"))
                .andExpect(status().isOk())
                .andExpect(content().string("20.5"));
    }

    @Test
    void calculateSumOptimized_ShouldReturnSum() throws Exception {
        when(studentService.calculateSumOptimized()).thenReturn(500000L);

        mockMvc.perform(get("/student/stream/sum-optimized"))
                .andExpect(status().isOk())
                .andExpect(content().string("500000"));
    }
}