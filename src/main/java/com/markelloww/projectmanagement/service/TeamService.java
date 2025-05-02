package com.markelloww.projectmanagement.service;

import com.markelloww.projectmanagement.model.Project;
import com.markelloww.projectmanagement.model.Team;
import com.markelloww.projectmanagement.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Markelloww
 */

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public void addTeam(Team team) {
        teamRepository.save(team);
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }
}
