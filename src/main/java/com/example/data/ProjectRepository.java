package com.example.data;

import com.example.domain.Project;

public interface ProjectRepository {
    Project find(Integer id);
    Project save(Project project);
}
