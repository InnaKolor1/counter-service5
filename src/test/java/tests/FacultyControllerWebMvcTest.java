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

import org.skypro.counter_service.controller.FacultyController;
import org.skypro.counter_service.model.Faculty;
import org.skypro.counter_service.service.FacultyService;
import java.util.Collections;

@WebMvcTest(FacultyController.class)
public class FacultyControllerWebMvcTest {


    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FacultyService facultyService;

    @Test
    public void testGetByName() throws Exception {
        Faculty faculty = new Faculty(1L, "Gryffindor", "Red", null);
        Mockito.when(facultyService.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.singletonList(faculty));

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/by-name")
                        .param("name", "Gryffindor")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Gryffindor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value("Red"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    @Test
    public void testGetByColor() throws Exception {
        Faculty faculty = new Faculty(1L, "Gryffindor", "Red", null);
        Mockito.when(facultyService.findByColor(ArgumentMatchers.anyString()))
                .thenReturn(Collections.singletonList(faculty));

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/by-color")
                        .param("color", "Red")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Gryffindor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value("Red"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

}

