package org.skypro.counter_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.skypro.counter_service.controller.AvatarController;
import org.skypro.counter_service.model.Avatar;
import org.skypro.counter_service.service.AvatarService;
import org.skypro.counter_service.service.FacultyService;
import org.skypro.counter_service.service.StudentService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
@WebMvcTest
class AvatarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AvatarService avatarService;

    @MockitoBean
    private FacultyService facultyService;

    @MockitoBean
    private StudentService studentService;

    @Test
    void uploadAvatar_ShouldReturnAvatar() throws Exception {
        Avatar avatar = new Avatar();
        avatar.setId(1L);
        avatar.setFilePath("/avatars/1.jpg");
        avatar.setMediaType("image/jpeg");
        avatar.setFileSize(1024L);

        when(avatarService.saveAvatar(any(Avatar.class))).thenReturn(avatar);

        mockMvc.perform(post("/avatar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(avatar)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.filePath").value("/avatars/1.jpg"));
    }

    @Test
    void getAvatarByStudentId_WhenExists_ShouldReturnAvatar() throws Exception {
        Avatar avatar = new Avatar();
        avatar.setId(1L);
        avatar.setFilePath("/avatars/1.jpg");

        when(avatarService.getAvatarByStudentId(1L)).thenReturn(Optional.of(avatar));

        mockMvc.perform(get("/avatar/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.filePath").value("/avatars/1.jpg"));
    }

    @Test
    void getAvatarByStudentId_WhenNotExists_ShouldReturnNotFound() throws Exception {
        when(avatarService.getAvatarByStudentId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/avatar/student/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void deleteAvatar_ShouldReturnOk() throws Exception {
        mockMvc.perform(delete("/avatar/1"))
                .andExpect(status().isOk());
    }
}