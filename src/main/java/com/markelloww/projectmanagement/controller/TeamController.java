package com.markelloww.projectmanagement.controller;

import com.markelloww.projectmanagement.model.Team;
import com.markelloww.projectmanagement.service.TeamService;
import com.markelloww.projectmanagement.service.UserService;
import jakarta.validation.Valid;
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
public class TeamController {
    private final UserService userService;
    private final TeamService teamService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("firstname", userService.getUserNameByEmail(email));
        model.addAttribute("teams", teamService.getTeams());
        return "index";
    }

    @GetMapping("/team/new")
    public String teamCreateForm() {
        return "team-new";
    }

    @GetMapping("/team/{id}")
    public String teamInfo(@PathVariable Long id, Model model) {
        Team team = teamService.getTeamById(id);
        if (team == null) {
            return "redirect:/";
        }
        model.addAttribute("teamName", team.getName());
        model.addAttribute("team", team);
        return "team-info";
    }

    @PostMapping("/team/new")
    public String teamCreate(Team team) {
        teamService.addTeam(team);
        return "redirect:/";
    }
}
