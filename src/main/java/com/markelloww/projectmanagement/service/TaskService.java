package com.markelloww.projectmanagement.service;

import com.markelloww.projectmanagement.model.Task;
import com.markelloww.projectmanagement.model.TaskStatus;
import com.markelloww.projectmanagement.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * @Author: Markelloww
 */

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskStatusService taskStatusService;
    private final ProjectService projectService;
    private final UserService userService;

    public Task getTaskById(Long task) {
        return taskRepository.findById(task)
                .orElseThrow(() -> new UsernameNotFoundException("Task not found"));
    }

    @Transactional
    public void createTask(Task task, Long projectId, String email) {
        task.setCreatedDate(LocalDateTime.now());
        task.setReporter(userService.getUserByEmail(email));
        task.setProject(projectService.getProjectById(projectId));
        task.setStatus(taskStatusService.getTaskStatusById(1L));
        taskRepository.save(task);
    }

    @Transactional
    public void updateStatus(Long taskId, Long statusId) {
        Task task = getTaskById(taskId);
        task.setStatus(taskStatusService.getTaskStatusById(statusId));
        taskRepository.save(task);
    }
}
