package org.skypro.counter_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.skypro.counter_service.model.Avatar;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);
}
