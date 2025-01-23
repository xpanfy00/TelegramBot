package com.carter.tgbot.repository;

import com.carter.tgbot.dto.ProjectDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectDto, Long> {
    List<ProjectDto> findProjectDtoById(int id);
    boolean existsById(int id);
    List<ProjectDto> findByDescriptionContaining(String description);
}
