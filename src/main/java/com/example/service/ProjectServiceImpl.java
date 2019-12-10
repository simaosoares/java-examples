package com.example.service;

import com.example.data.ProjectRepository;
import com.example.domain.Project;

public class ProjectServiceImpl implements ProjectService {

    private final ProjectServiceMapper projectServiceMapper;
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectServiceMapper projectServiceMapper, ProjectRepository projectRepository) {
        this.projectServiceMapper = projectServiceMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public void updateProject(int projectId, ProjectSaveDto projectDto) {
        final Project project = projectRepository.find(projectId);
        projectServiceMapper.mapDtoToEntity(projectDto, project);
        projectRepository.save(project);
    }
}
