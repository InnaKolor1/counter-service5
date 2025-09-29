package org.skypro.counter_service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.skypro.counter_service.model.Avatar;
import org.skypro.counter_service.repository.AvatarRepository;
import java.util.Optional;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Avatar saveAvatar(Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    public Optional<Avatar> getAvatarByStudentId(Long studentId) {
        return avatarRepository.findByStudentId(studentId);
    }

    public void deleteAvatar(Long id) {
        avatarRepository.deleteById(id);
    }
}