package com.markelloww.projectmanagement.repository;

import com.markelloww.projectmanagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Markelloww
 */

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
