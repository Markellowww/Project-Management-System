package com.markelloww.projectmanagement.controller;

import com.markelloww.projectmanagement.model.Team;
import com.markelloww.projectmanagement.model.User;
import com.markelloww.projectmanagement.repository.UserRepository;
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
    private final UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("firstname", userService.getUserNameByEmail(email));
        model.addAttribute("teams", teamService.getTeams());
        return "index";
    }

    @GetMapping("/team/new")
    public String teamCreateForm(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("firstname", userService.getUserNameByEmail(email));
        return "team-new";
    }

    @GetMapping("/team/{id}")
    public String teamInfo(@PathVariable Long id, Model model, Principal principal) {
        Team team = teamService.getTeamById(id);
        if (team == null) {
            return "redirect:/";
        }
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("team", team);
        model.addAttribute("email", principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("isOwner", team.getOwner().getId().equals(user.getId()));
        model.addAttribute("isMember", team.getMembers().stream()
                .anyMatch(member -> member.getId().equals(user.getId())));
        return "team-info";
    }

    @PostMapping("/team/new")
    public String teamCreate(Team team, Principal principal) {
        teamService.createTeam(team, principal);
        return "redirect:/";
    }

    @PostMapping("/team/{id}/delete")
    public String deleteTeam(@PathVariable Long id, Principal principal) {
        String email = principal.getName();
        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Team team = teamService.getTeamById(id);
        if (team != null && team.getOwner().getId().equals(currentUser.getId())) {
            teamService.deleteTeam(id);
        }
        return "redirect:/";
    }

    @PostMapping("/team/{id}/join")
    public String joinTeam(@PathVariable Long id, Principal principal) {
        teamService.joinTeam(id, principal.getName());
        return "redirect:/team/" + id;
    }

    @PostMapping("/team/{id}/leave")
    public String leaveTeam(@PathVariable Long id, Principal principal) {
        teamService.leaveTeam(id, principal.getName());
        return "redirect:/team/" + id;
    }
}