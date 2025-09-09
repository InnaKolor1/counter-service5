package org.skypro.counter_service.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import org.skypro.counter_service.model.Faculty;
import org.skypro.counter_service.resources.FacultyRepository;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FacultyControllerTestRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FacultyRepository facultyRepository;

    @BeforeEach
    void setUp() {
        Faculty faculty = new Faculty();
        faculty.setName("Test");
        faculty.setColor("Red");
        facultyRepository.save(faculty);
    }

    @Test
    public void testGetByName() {
        String url = "http://localhost:" + port + "/faculty/by-name?name=Test";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getBody().contains("Test"));
    }

    @Test
    public void testGetByColor() {
        String url = "http://localhost:" + port + "/faculty/by-color?color=Red";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getBody().contains("Red"));
    }

    @Test
    public void testSearchWithNameOrColor() {
        String url = "http://localhost:" + port + "/faculty/search?nameOrColor=Red";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getBody().contains("Red"));
    }
}