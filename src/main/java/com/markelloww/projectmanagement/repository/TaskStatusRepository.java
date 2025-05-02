package com.markelloww.projectmanagement.repository;

import com.markelloww.projectmanagement.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Markelloww
 */

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
}
