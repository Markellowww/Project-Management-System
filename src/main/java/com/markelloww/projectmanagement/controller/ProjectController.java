package com.markelloww.projectmanagement.controller;

import com.markelloww.projectmanagement.model.Project;
import com.markelloww.projectmanagement.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@RequestMapping("/team/{teamId}/project")
public class ProjectController {
    public static final String REDIRECT = "redirect:/";

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
            return REDIRECT;
        }

        Project project = projectService.getProjectById(projectId);
        LocalDateTime start = project.getStartDate();
        LocalDateTime end = project.getEndDate();

        model.addAttribute("project", project);
        model.addAttribute("isOwner",
                project.getTeam().getOwner().getEmail().equals(principal.getName()));
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
            return REDIRECT;
        }
        model.addAttribute("team", teamService.getTeamById(teamId));
        return "project-new";
    }

    @PostMapping("/new")
    public String createProject(@PathVariable Long teamId, Project project, Principal principal) {
        if (!teamService.checkUser(teamId, principal.getName())) {
            return REDIRECT;
        }
        projectService.createProject(project, teamId, principal);
        return "redirect:/team/" + teamId;
    }

    @PostMapping("/{projectId}/delete")
    public String deleteProject(@PathVariable Long teamId, @PathVariable Long projectId) {
        projectService.deleteProject(projectService.getProjectById(projectId));
        return "redirect:/team/" + teamId;
    }
}
