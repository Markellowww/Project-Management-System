package com.markelloww.projectmanagement.service;

import com.markelloww.projectmanagement.controller.ProjectController;
import com.markelloww.projectmanagement.model.Project;
import com.markelloww.projectmanagement.model.Team;
import com.markelloww.projectmanagement.model.User;
import com.markelloww.projectmanagement.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * @Author: Markelloww
 */

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TeamService teamService;
    private final UserService userService;

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new UsernameNotFoundException("Project not found"));
    }

    @Transactional
    public void createProject(Project project, Long teamId, Principal principal) {
        Team team = teamService.getTeamById(teamId);
        User user = userService.getUserByEmail(principal.getName());
        project.setTeam(team);
        project.setStartDate(LocalDateTime.now());
        project.setOwner(user);
        projectRepository.save(project);
    }
}
