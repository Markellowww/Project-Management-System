package com.markelloww.projectmanagement.service;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.markelloww.projectmanagement.model.Team;
import com.markelloww.projectmanagement.model.User;
import com.markelloww.projectmanagement.repository.TeamRepository;
import com.markelloww.projectmanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: Markelloww
 */

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final ParameterNamesModule parameterNamesModule;
    private final UserService userService;

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Team not found"));
    }

    public boolean checkUser(Long teamId, String email) {
        Team team = getTeamById(teamId);
        User user = userService.getUserByEmail(email);
        return team != null && team.getMembers().contains(user);
    }

    @Transactional
    public void createTeam(Team team, Principal principal) {
        User owner = userService.getUserByEmail(principal.getName());
        team.setOwner(owner);
        Team savedTeam = teamRepository.save(team);
        savedTeam.addMember(owner);
        teamRepository.save(savedTeam);
    }

    @Transactional
    public void deleteTeam(Long teamId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Team team = getTeamById(teamId);
        if (team.getOwner().getId().equals(user.getId())) {
            for (User member : new HashSet<>(team.getMembers())) {
                team.removeMember(member);
            }
            teamRepository.delete(team);
        }
    }

    @Transactional
    public void joinTeam(Long teamId, User user) {
        Team team = getTeamById(teamId);
        if (!team.getMembers().contains(user)) {
            team.addMember(user);
            teamRepository.save(team);
        }
    }

    @Transactional
    public void leaveTeam(Long teamId, User user) {
        Team team = getTeamById(teamId);
        if (team.getMembers().contains(user) && !team.getOwner().equals(user)) {
            team.removeMember(user);
            teamRepository.save(team);
        }
    }
}
