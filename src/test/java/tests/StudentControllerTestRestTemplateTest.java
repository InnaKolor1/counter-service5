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
import org.skypro.counter_service.model.Student;
import org.skypro.counter_service.repository.FacultyRepository;
import org.skypro.counter_service.repository.StudentRepository;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StudentControllerTestRestTemplateTest {



    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
        facultyRepository.deleteAll();

        Faculty gryffindor = facultyRepository.save(new Faculty(null, "Gryffindor", "Red", null));
        Faculty slytherin = facultyRepository.save(new Faculty(null, "Slytherin", "Green", null));

        Student harry = new Student(null, "Harry Potter", 17, gryffindor);
        Student hermione = new Student(null, "Hermione Granger", 18, gryffindor);
        Student draco = new Student(null, "Draco Malfoy", 17, slytherin);

        studentRepository.saveAll(List.of(harry, hermione, draco));
    }

    @Test
    public void testGetStudentsByAgeBetween() {
        String url = "http://localhost:" + port + "/student/age-between?minAge=16&maxAge=18";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(3, response.getBody().size());


        List<Student> students = studentRepository.findByAgeBetween(16, 18);
        Assertions.assertEquals(3, students.size());
        Assertions.assertTrue(students.stream().anyMatch(s -> s.getName().equals("Harry Potter")));
        Assertions.assertTrue(students.stream().anyMatch(s -> s.getName().equals("Hermione Granger")));
        Assertions.assertTrue(students.stream().anyMatch(s -> s.getName().equals("Draco Malfoy")));
    }

    @Test
    public void testGetStudentsByFaculty() {
        Faculty gryffindor = facultyRepository.findByNameIgnoreCase("Gryffindor").get(0);

        String url = "http://localhost:" + port + "/student/by-faculty?facultyId=" + gryffindor.getId();
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());


        List<Student> students = studentRepository.findByFacultyId(gryffindor.getId());
        Assertions.assertEquals(2, students.size());
        Assertions.assertTrue(students.stream().anyMatch(s -> s.getName().equals("Harry Potter")));
        Assertions.assertTrue(students.stream().anyMatch(s -> s.getName().equals("Hermione Granger")));
    }
}