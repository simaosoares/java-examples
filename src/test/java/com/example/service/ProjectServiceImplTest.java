package com.example.service;

import com.example.data.ProjectRepository;
import com.example.domain.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImplTest {

    @Mock
    private ProjectServiceMapper projectServiceMapper;
    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    public void updateProject() {
        // arrange data
        final ProjectSaveDto projectDto = new ProjectSaveDto();
        projectDto.setName("Project 1");
        given(projectRepository.find(eq(1))).willReturn(new Project());
        doAnswer(invocation -> {
            final Project output = invocation.getArgument(1);
            output.setName(projectDto.getName());
            return null;
        }).when(projectServiceMapper).mapDtoToEntity(any(), any());
        given(projectRepository.save(argThat(argument -> argument.getName().equals(projectDto.getName())))).willReturn(null);

        // act
        projectService.updateProject(1, projectDto);

        // assert
        ArgumentCaptor<Project> argumentCaptor = ArgumentCaptor.forClass(Project.class);
        verify(projectRepository, times(1)).save(argumentCaptor.capture());
        assertEquals("Project 1", argumentCaptor.getValue().getName());
    }
}
