package com.markelloww.projectmanagement.controller;

import com.markelloww.projectmanagement.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: Markelloww
 */

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("projects", projectService.getProjects());
        return "index";
    }
}
