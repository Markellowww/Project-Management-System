package com.markelloww.projectmanagement.service;

import com.markelloww.projectmanagement.model.Project;
import com.markelloww.projectmanagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Markelloww
 */

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public void addProject(Project project) {
        projectRepository.save(project);
    }

    public void removeProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }
}
