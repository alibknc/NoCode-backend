package com.kou.bitirme.data.repository;

import com.kou.bitirme.data.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query("select p from Project p where p.userId = ?1")
    List<Project> findAllProjects(String userId);

    @Query("select p from Project p where p.apiKey = ?1")
    Project getProjectByKey(String key);

}
