package com.markelloww.projectmanagement.controller;

import com.markelloww.projectmanagement.model.Project;
import com.markelloww.projectmanagement.service.ProjectService;
import com.markelloww.projectmanagement.service.TaskService;
import com.markelloww.projectmanagement.service.TaskStatusService;
import com.markelloww.projectmanagement.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: Markelloww
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/team/{teamId}/project")
public class ProjectController {
    private final TeamService teamService;
    private final ProjectService projectService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final TaskStatusService taskStatusService;
    private final TaskService taskService;


    @GetMapping("/{projectId}")
    public String projectInfo(@PathVariable Long projectId,
                              @PathVariable Long teamId,
                              Principal principal,
                              Model model) {
        if (!teamService.checkUser(teamId, principal.getName())) {
            return "redirect:/";
        }

        Project project = projectService.getProjectById(projectId);
        LocalDateTime start = project.getStartDate();
        LocalDateTime end = project.getEndDate();

        model.addAttribute("project", project);
        model.addAttribute("tasks", project.getTasks());
        model.addAttribute("taskStatuses", taskStatusService.getTaskStatuses());
        model.addAttribute("timeLeft", Duration.between(start, end));
        model.addAttribute("startTime", start.format(formatter));
        model.addAttribute("endTime", end.format(formatter));

        model.addAttribute("taskAmount", taskService.getTaskAmount(project));
        model.addAttribute("taskStatus", taskService.getTaskStatusesAmount(project));
        return "project-info";
    }

    @GetMapping("/new")
    public String showProjectCreate(@PathVariable Long teamId, Model model, Principal principal) {
        if (!teamService.checkUser(teamId, principal.getName())) {
            return "redirect:/";
        }
        model.addAttribute("team", teamService.getTeamById(teamId));
        return "project-new";
    }

    @PostMapping("/new")
    public String createProject(@PathVariable Long teamId, Project project, Principal principal) {
        if (!teamService.checkUser(teamId, principal.getName())) {
            return "redirect:/";
        }
        projectService.createProject(project, teamId, principal);
        return "redirect:/team/" + teamId;
    }
}
