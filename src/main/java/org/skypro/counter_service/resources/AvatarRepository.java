package org.skypro.counter_service.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.skypro.counter_service.model.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

}