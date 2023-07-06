package com.kou.bitirme.service;

import com.kou.bitirme.data.entity.Project;
import com.kou.bitirme.data.repository.ProjectRepository;
import com.kou.bitirme.dto.request.CreateTableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TableService {

    private final JdbcTemplate jdbcTemplate;

    private final ProjectRepository projectRepository;

    public Map<String, Object> getTables(UUID id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("project not found"));

        Map<String, Object> result = new HashMap<>();
        result.put("projectId", project.getId());
        result.put("projectTitle", project.getTitle());
        result.put("apiKey", project.getApiKey());
        result.put("tables", new ArrayList<>());

        jdbcTemplate.query("SELECT table_name FROM information_schema.tables WHERE table_schema = ?", new Object[]{project.getSchema()}, resultSet -> {
            List<String> tables = new ArrayList<>();

            do {
                String tableName = resultSet.getString(1);
                tables.add(tableName);
            } while (resultSet.next());

            result.put("tables", tables);
        });

        return result;
    }

    public void createTable(CreateTableRequest request) {
        Project project = projectRepository.findById(request.getProjectId()).orElseThrow(() -> new RuntimeException("project not found"));

        StringBuilder query = new StringBuilder("CREATE TABLE ").append(project.getSchema()).append(".").append(request.getTitle()).append(" (");
        request.getColumns().forEach(c -> query.append(c.get("columnName")).append(" ").append(c.get("dataType")).append(", "));
        query.setLength(query.length() - 2);
        query.append(")");
        jdbcTemplate.execute(query.toString());
    }

    public Map<String, Object> getTableByName(UUID projectId, String tableName) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("project not found"));

        List<Map<String, Object>> columns = new ArrayList<>();

        String query = "SELECT * FROM information_schema.columns WHERE table_schema = ? AND table_name = ?";
        jdbcTemplate.query(query, new Object[]{project.getSchema(), tableName}, resultSet -> {
            do {
                String columnName = resultSet.getString("column_name");
                String dataType = resultSet.getString("data_type");

                Map<String, Object> column = new HashMap<>();
                column.put("columnName", columnName);
                column.put("dataType", dataType);

                columns.add(column);
            } while (resultSet.next());
        });

        Map<String, Object> result = new HashMap<>();
        result.put("tableName", tableName);
        result.put("projectTitle", project.getTitle());
        result.put("columns", columns);

        query = "SELECT * FROM " + project.getSchema() + "." + tableName;
        var data = jdbcTemplate.query(query, new TableDataRowMapper());

        result.put("data", data);

        return result;
    }

    private static class TableDataRowMapper implements RowMapper<Map<String, Object>> {

        @Override
        public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            Map<String, Object> row = new HashMap<>();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object value = rs.getObject(i);
                row.put(columnName, value);
            }

            return row;
        }
    }

}
