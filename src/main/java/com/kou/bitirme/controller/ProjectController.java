package com.kou.bitirme.controller;

import com.kou.bitirme.dto.request.CreateProjectRequest;
import com.kou.bitirme.dto.response.ProjectDto;
import com.kou.bitirme.service.ProjectService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects(String userId) {
        return ResponseEntity.ok(projectService.getAllProjects(userId));
    }

    @PostMapping
    public ResponseEntity<Void> createProject(@Valid @RequestBody CreateProjectRequest project) {
        projectService.createProject(project);
        return ResponseEntity.ok().build();
    }

}
