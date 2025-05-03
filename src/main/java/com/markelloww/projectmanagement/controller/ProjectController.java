package com.markelloww.projectmanagement.controller;

import com.markelloww.projectmanagement.model.Project;
import com.markelloww.projectmanagement.service.ProjectService;
import com.markelloww.projectmanagement.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @Author: Markelloww
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/team/{teamId}/project")
public class ProjectController {
    private final TeamService teamService;
    private final ProjectService projectService;

    @GetMapping("/{projectId}")
    public String projectInfo(@PathVariable Long projectId,
                              @PathVariable Long teamId,
                              Principal principal,
                              Model model) {
        if (!teamService.checkUser(teamId, principal.getName())) {
            return "redirect:/";
        }
        model.addAttribute("project", projectService.getProjectById(projectId));
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
