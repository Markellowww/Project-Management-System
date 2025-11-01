package com.markelloww.projectmanagement.controller;

import com.markelloww.projectmanagement.model.Team;
import com.markelloww.projectmanagement.model.User;
import com.markelloww.projectmanagement.service.TeamService;
import com.markelloww.projectmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TeamController {
    public static final String REDIRECT_TEAM = "redirect:/team/";

    private final UserService userService;
    private final TeamService teamService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("teams", teamService.getTeams());
        return "index";
    }

    @GetMapping("/team/new")
    public String teamCreateForm(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "team-new";
    }

    @PostMapping("/team/new")
    public String teamCreate(Team team, Principal principal) {
        teamService.createTeam(team, principal);
        return "redirect:/";
    }

    @GetMapping("/team/{teamId}")
    public String teamInfo(@PathVariable Long teamId, Model model, Principal principal) {
        Team team = teamService.getTeamById(teamId);
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("team", team);
        model.addAttribute("user", user);
        model.addAttribute("projects", team.getProjects());
        model.addAttribute("isOwner", team.getOwner().getId().equals(user.getId()));
        model.addAttribute("isMember", team.getMembers().stream()
                .anyMatch(member -> member.getId().equals(user.getId())));
        return "team-info";
    }

    @PostMapping("/team/{teamId}/delete")
    public String deleteTeam(@PathVariable Long teamId, Principal principal) {
        teamService.deleteTeam(teamId, principal);
        return "redirect:/";
    }

    @PostMapping("/team/{teamId}/join")
    public String joinTeam(@PathVariable Long teamId, Principal principal) {
        teamService.joinTeam(teamId, userService.getUserByEmail(principal.getName()));
        return REDIRECT_TEAM + teamId;
    }

    @PostMapping("/team/{teamId}/leave")
    public String leaveTeam(@PathVariable Long teamId, Principal principal) {
        teamService.leaveTeam(teamId, userService.getUserByEmail(principal.getName()));
        return REDIRECT_TEAM + teamId;
    }

    @PostMapping("/team/{teamId}/kick/{userId}")
    public String removeMember(@PathVariable Long teamId, @PathVariable Long userId) {
        teamService.leaveTeam(teamId, userService.getUserById(userId));
        return REDIRECT_TEAM + teamId;
    }
}