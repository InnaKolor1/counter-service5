package tests;

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
import org.skypro.counter_service.repository.FacultyRepository;
import java.util.List;


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
        facultyRepository.deleteAll();

        Faculty gryffindor = new Faculty(null, "Gryffindor", "Red", null);
        Faculty slytherin = new Faculty(null, "Slytherin", "Green", null);

        facultyRepository.saveAll(List.of(gryffindor, slytherin));
    }

    @Test
    public void testGetByName() {
        String url = "http://localhost:" + port + "/faculty/by-name?name=Gryffindor";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(1, response.getBody().size());


        List<Faculty> faculties = facultyRepository.findByNameIgnoreCase("Gryffindor");
        Assertions.assertEquals("Gryffindor", faculties.get(0).getName());
        Assertions.assertEquals("Red", faculties.get(0).getColor());
    }

    @Test
    public void testGetByColor() {
        String url = "http://localhost:" + port + "/faculty/by-color?color=Red";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(1, response.getBody().size());

        List<Faculty> faculties = facultyRepository.findByColorIgnoreCase("Red");
        Assertions.assertEquals("Gryffindor", faculties.get(0).getName());
        Assertions.assertEquals("Red", faculties.get(0).getColor());
    }

    @Test
    public void testSearchWithNameAndColor() {
        String url = "http://localhost:" + port + "/faculty/search?name=Gryffindor&color=Red";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(1, response.getBody().size());


        List<Faculty> faculties = facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase("Gryffindor", "Red");
        Assertions.assertEquals("Gryffindor", faculties.get(0).getName());
    }
}