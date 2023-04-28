package com.kou.bitirme.dto.mapper;

import com.kou.bitirme.data.entity.Project;
import com.kou.bitirme.dto.request.CreateProjectRequest;
import com.kou.bitirme.dto.response.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(implementationName = "ProjectMapperImpl", componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

    ProjectDto toProjectDto(Project project);

    Project toProject(CreateProjectRequest projectRequest);

}
