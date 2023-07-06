package com.kou.bitirme.service;

import com.itfsw.query.builder.SqlQueryBuilderFactory;
import com.itfsw.query.builder.support.builder.SqlBuilder;
import com.itfsw.query.builder.support.model.result.SqlQueryResult;
import com.kou.bitirme.data.entity.Endpoint;
import com.kou.bitirme.data.entity.Project;
import com.kou.bitirme.data.entity.enums.RestType;
import com.kou.bitirme.data.repository.EndpointRepository;
import com.kou.bitirme.data.repository.ProjectRepository;
import com.kou.bitirme.dto.mapper.EndpointMapper;
import com.kou.bitirme.dto.request.CreateEndpointRequest;
import com.kou.bitirme.dto.response.EndpointDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EndpointService {

    private final EndpointRepository endpointRepository;
    private final ProjectRepository projectRepository;
    private final EndpointMapper endpointMapper;
    private final JdbcTemplate jdbcTemplate;
    private final TableService tableService;

    public Object runQuery(String url, String key, Map<String, Object> body, String requestType) {
        Project project = projectRepository.getProjectByKey(key);
        Endpoint endpoint = endpointRepository.getEndpointByProjectIdAndUrl(project.getId().toString(), url);

        if (endpoint.getType() == RestType.GET && Objects.equals(requestType, "GET"))
            return jdbcTemplate.queryForList(endpoint.getQuery());
        else if (endpoint.getType() == RestType.POST && Objects.equals(requestType, "POST")) {
            StringBuilder columns = new StringBuilder("");
            body.keySet().forEach(c -> columns.append(c).append(", "));
            columns.setLength(columns.length() - 2);

            StringBuilder values = new StringBuilder("");
            body.values().forEach(v -> values.append("'").append(v).append("'").append(", "));
            values.setLength(values.length() - 2);

            endpoint.setQuery(endpoint.getQuery().replace("column", columns));
            endpoint.setQuery(endpoint.getQuery().replace("value", values));

            return jdbcTemplate.update(endpoint.getQuery());
        }

        return "Gecersiz İstek!";
    }

    @SneakyThrows
    public void createEndpoint(CreateEndpointRequest request) {
        Project project = projectRepository.findById(UUID.fromString(request.getProjectId())).orElseThrow(() -> new RuntimeException("project not found!"));

        if (request.getType() == RestType.GET) {
            SqlQueryBuilderFactory sqlQueryBuilderFactory = new SqlQueryBuilderFactory();
            SqlBuilder sqlBuilder = sqlQueryBuilderFactory.builder();

            SqlQueryResult sqlQueryResult = sqlBuilder.build(request.getQuery());

            String query = new StringBuilder("SELECT * FROM ").append(project.getSchema()).append(".").append(request.getTable()).append(" WHERE ").append(sqlQueryResult.toString()).toString();

            for (int i = 0; i < sqlQueryResult.getParams().size(); i++) {
                query = query.replaceFirst("\\?", sqlQueryResult.getParams().get(i).toString());
            }

            request.setQuery(query);
        } else {
            StringBuilder query = new StringBuilder("INSERT INTO ").append(project.getSchema()).append(".").append(request.getTable()).append(" (column) VALUES (value)");
            request.setQuery(query.toString());
        }

        endpointRepository.save(endpointMapper.toEndpoint(request));
    }

    public List<EndpointDto> getAllEndpoints(UUID projectId) {
        List<Endpoint> endpointList = endpointRepository.findAllByProjectId(projectId.toString());

        return endpointList.stream()
                .map(endpointMapper::toEndpointDto).toList();
    }

}
