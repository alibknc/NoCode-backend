package com.kou.bitirme.service;

import com.kou.bitirme.data.entity.Project;
import com.kou.bitirme.data.repository.ProjectRepository;
import com.kou.bitirme.dto.mapper.ProjectMapper;
import com.kou.bitirme.dto.request.CreateProjectRequest;
import com.kou.bitirme.dto.response.ProjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final JdbcTemplate jdbcTemplate;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper, JdbcTemplate jdbcTemplate) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProjectDto> getAllProjects(String userId) {
        List<Project> projectList = projectRepository.findAllProjects(userId);

        return projectList.stream()
                .map(projectMapper::toProjectDto).toList();
    }

    public void createProject(CreateProjectRequest request) {
        jdbcTemplate.execute("CREATE SCHEMA " + request.getTitle());

        Project project = projectMapper.toProject(request);
        projectRepository.save(project);
    }

}
