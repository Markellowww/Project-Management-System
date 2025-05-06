package com.markelloww.projectmanagement.service;

import com.markelloww.projectmanagement.model.Project;
import com.markelloww.projectmanagement.model.Task;
import com.markelloww.projectmanagement.model.TaskStatus;
import com.markelloww.projectmanagement.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public int getTaskAmount(Project project) {
        if (project == null || project.getTasks() == null) {
            return 0;
        }
        return project.getTasks().size();
    }

    public List<Integer> getTaskStatusesAmount(Project project) {
        List<Integer> statusCounts = new ArrayList<>();

        if (project == null || project.getTasks() == null) {
            return Arrays.asList(0, 0, 0, 0, 0);
        }

        Map<Long, Long> countsMap = project.getTasks().stream()
                .filter(task -> task.getStatus() != null)
                .collect(Collectors.groupingBy(
                        task -> task.getStatus().getId(),
                        Collectors.counting()
                ));

        for (long statusId = 2; statusId <= 6; statusId++) {
            Long count = countsMap.getOrDefault(statusId, 0L);
            statusCounts.add(count.intValue());
        }

        return statusCounts;
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
