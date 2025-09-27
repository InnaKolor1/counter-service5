package org.skypro.counter_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.skypro.counter_service.model.Avatar;
import org.skypro.counter_service.service.AvatarService;

import java.util.List;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private final AvatarService avatarService;
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping
    public Avatar uploadAvatar(@RequestBody Avatar avatar) {
        return avatarService.saveAvatar(avatar);
    }

    @GetMapping("/student/{studentId}")
    public Avatar getAvatarByStudentId(@PathVariable Long studentId) {
        return avatarService.getAvatarByStudentId(studentId).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteAvatar(@PathVariable Long id) {
        avatarService.deleteAvatar(id);
    }
}