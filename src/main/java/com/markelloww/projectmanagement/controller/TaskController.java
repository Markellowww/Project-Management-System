package com.markelloww.projectmanagement.controller;

import com.markelloww.projectmanagement.model.Task;
import com.markelloww.projectmanagement.service.ProjectService;
import com.markelloww.projectmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/team/{teamId}/project/{projectId}/task")
public class TaskController {
    public static final String REDIRECT_TEAM = "redirect:/team/";
    public static final String PROJECT = "/project/";

    private final TaskService taskService;
    private final ProjectService projectService;

    @GetMapping("/new")
    public String showCreateTask(@PathVariable Long projectId, @PathVariable Long teamId,
                                 Model model) {
        model.addAttribute("project", projectService.getProjectById(projectId));
        return "task-new";
    }

    @PostMapping("/new")
    public String createTask(@PathVariable Long projectId, @PathVariable Long teamId,
                             Task task, Principal principal) {
        taskService.createTask(task, projectId, principal.getName());
        return REDIRECT_TEAM + teamId + PROJECT + projectId;
    }

    @PostMapping("/update")
    public String updateTaskStatus(@PathVariable Long teamId, @PathVariable Long projectId,
                                   @RequestParam Long taskId, @RequestParam Long statusId ) {
        taskService.updateStatus(taskId, statusId);
        return REDIRECT_TEAM + teamId + PROJECT + projectId;
    }

    @PostMapping("/delete")
    public String deleteTask(@PathVariable Long teamId, @PathVariable Long projectId, Principal principal,
                             @RequestParam Long taskId) {
        taskService.deleteTask(taskService.getTaskById(taskId), principal.getName());
        return REDIRECT_TEAM + teamId + PROJECT + projectId;
    }
}
