package com.example.service;

import com.example.domain.Project;

public class ProjectServiceMapperImpl implements ProjectServiceMapper {

    @Override
    public void mapDtoToEntity(ProjectSaveDto projectSaveDto, Project project) {
        project.setName(projectSaveDto.getName());
    }
}
