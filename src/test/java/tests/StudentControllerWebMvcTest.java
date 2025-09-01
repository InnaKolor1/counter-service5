package tests;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.skypro.counter_service.controller.StudentController;
import org.skypro.counter_service.model.Student;
import org.skypro.counter_service.service.StudentService;
import java.util.Collections;

@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @Test
    public void testGetStudentsByAgeBetween() throws Exception {
        Student student = new Student(1L, "Harry Potter", 17, null);
        Mockito.when(studentService.findByAgeBetween(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(Collections.singletonList(student));

        mockMvc.perform(MockMvcRequestBuilders.get("/student/age-between")
                        .param("minAge", "16")
                        .param("maxAge", "18")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Harry Potter"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(17))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    @Test
    public void testGetStudentsByFaculty() throws Exception {
        Student student = new Student(1L, "Harry Potter", 17, null);
        Mockito.when(studentService.findByFacultyId(ArgumentMatchers.anyLong()))
                .thenReturn(Collections.singletonList(student));

        mockMvc.perform(MockMvcRequestBuilders.get("/student/by-faculty")
                        .param("facultyId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Harry Potter"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(17))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }


}

