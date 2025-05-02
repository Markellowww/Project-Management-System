package com.markelloww.projectmanagement.controller;

import com.markelloww.projectmanagement.model.Project;
import com.markelloww.projectmanagement.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

/**
 * @Author: Markelloww
 */

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("userEmail", email);
        model.addAttribute("projects", projectService.getProjects());
        return "index";
    }

    @GetMapping("/project/new")
    public String showCreateForm() {
        return "project-new";
    }

    @GetMapping("/projects/{id}")
    public String projectInfo(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            return "redirect:/";
        }
        model.addAttribute("project", project);
        return "project-info";
    }

    @PostMapping("/project/new")
    public String projectCreate(Project project) {
        projectService.addProject(project);
        return "redirect:/";
    }
}
