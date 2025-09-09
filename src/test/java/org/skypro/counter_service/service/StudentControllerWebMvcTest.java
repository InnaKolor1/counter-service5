package org.skypro.counter_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.counter_service.model.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.skypro.counter_service.controller.StudentController;
import org.skypro.counter_service.model.Student;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    private Student testStudent;
    private Faculty testFaculty;

    @BeforeEach
    void setUp() {
        testFaculty = new Faculty();
        testFaculty.setId(1L);
        testFaculty.setName("Gryffindor");
        testFaculty.setColor("Red");

        testStudent = new Student();
        testStudent.setId(1L);
        testStudent.setName("Harry Potter");
        testStudent.setAge(17);
        testStudent.setFaculty(testFaculty);
    }

    @Test
    void testGetStudentById() throws Exception {
        when(studentService.findById(1L)).thenReturn(testStudent);


        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").value(1))
                .andExpect((ResultMatcher) jsonPath("$.name").value("Harry Potter"))
                .andExpect((ResultMatcher) jsonPath("$.age").value(17))
                .andExpect((ResultMatcher) jsonPath("$.faculty.name").value("Gryffindor"));
    }

    @Test
    void testGetStudentsByAgeBetween() throws Exception {
        when(studentService.findByAgeBetween(16, 18)).thenReturn(List.of(testStudent));

        mockMvc.perform(get("/student/age-between")
                        .param("minAge", "16")
                        .param("maxAge", "18"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1))
                .andExpect((ResultMatcher) jsonPath("$[0].name").value("Harry Potter"));
    }

    @Test
    void testGetStudentsByFaculty() throws Exception {
        when(studentService.findByFacultyId(1L)).thenReturn(List.of(testStudent));

        mockMvc.perform(get("/student/by-faculty")
                        .param("facultyId", "1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1))
                .andExpect((ResultMatcher) jsonPath("$[0].name").value("Harry Potter"));
    }
}