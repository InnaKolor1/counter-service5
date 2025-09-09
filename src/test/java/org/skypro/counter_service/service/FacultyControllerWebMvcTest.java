package org.skypro.counter_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.skypro.counter_service.controller.FacultyController;
import org.skypro.counter_service.model.Faculty;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(FacultyController.class)
class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FacultyService facultyService;

    private Faculty testFaculty;


    @BeforeEach
    void setUp() {
        testFaculty = new Faculty();
        testFaculty.setId(1L);
        testFaculty.setName("Gryffindor");
        testFaculty.setColor("Red");
    }

    @Test
    void testGetByColor() throws Exception {
        when(facultyService.getByColor("Red"))
                .thenReturn(List.of(testFaculty));


        mockMvc.perform(get("/faculty/by-color")
                        .param("color", "Red"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1))
                .andExpect((ResultMatcher) jsonPath("$[0].name").value("Gryffindor"))
                .andExpect((ResultMatcher) jsonPath("$[0].color").value("Red"));
    }

    @Test
    void testGetByName() throws Exception {
        when(facultyService.getByName("Gryffindor"))
                .thenReturn(List.of(testFaculty));

        mockMvc.perform(get("/faculty/search")
                        .param("nameOrColor", "Gryffindor"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1))
                .andExpect((ResultMatcher) jsonPath("$[0].name").value("Gryffindor"))
                .andExpect((ResultMatcher) jsonPath("$[0].color").value("Red"));
    }

    @Test
    void testSearchByNameOrColor() throws Exception {
        when(facultyService.findByNameOrColor("Red"))
                .thenReturn(List.of(testFaculty));

        mockMvc.perform(get("/faculty/search")
                        .param("nameOrColor", "Red"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1))
                .andExpect((ResultMatcher) jsonPath("$[0].name").value("Gryffindor"))
                .andExpect((ResultMatcher) jsonPath("$[0].color").value("Red"));
    }
}