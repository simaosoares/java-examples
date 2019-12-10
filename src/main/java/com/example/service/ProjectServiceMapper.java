package com.example.service;

import com.example.domain.Project;

public interface ProjectServiceMapper {

    void mapDtoToEntity(ProjectSaveDto projectDto, Project project);
}
