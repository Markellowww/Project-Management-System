package com.markelloww.projectmanagement.service;

import com.markelloww.projectmanagement.model.TaskStatus;
import com.markelloww.projectmanagement.repository.TaskStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Markelloww
 */

@Service
@RequiredArgsConstructor
public class TaskStatusService {
    private final TaskStatusRepository taskStatusRepository;

    public List<TaskStatus> getTaskStatuses() {
        return taskStatusRepository.findAll();
    }

    public TaskStatus getTaskStatusById(Long taskStatus) {
        return taskStatusRepository.findById(taskStatus)
                .orElseThrow(() -> new UsernameNotFoundException("TaskStatus not found"));
    }
}
