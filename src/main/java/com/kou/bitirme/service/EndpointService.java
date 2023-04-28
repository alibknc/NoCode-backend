package com.kou.bitirme.service;

import com.kou.bitirme.data.entity.Endpoint;
import com.kou.bitirme.data.entity.Project;
import com.kou.bitirme.data.repository.EndpointRepository;
import com.kou.bitirme.data.repository.ProjectRepository;
import com.kou.bitirme.dto.mapper.EndpointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EndpointService {

    private final EndpointRepository endpointRepository;
    private final ProjectRepository projectRepository;
    private final EndpointMapper endpointMapper;
    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> runQuery(String url, String key) {
        Project project = projectRepository.getProjectByKey(key);
        Endpoint endpoint = endpointRepository.getEndpointByProjectIdAndKey(project.getId().toString(), url);

        return jdbcTemplate.queryForList(endpoint.getQuery());
    }

}
