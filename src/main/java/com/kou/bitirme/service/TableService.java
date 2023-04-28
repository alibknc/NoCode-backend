package com.kou.bitirme.service;

import com.kou.bitirme.dto.request.CreateTableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {

    private final JdbcTemplate jdbcTemplate;

    public List<String> getTables(String projectId) {
        return jdbcTemplate.queryForList(
                "SELECT table_name FROM information_schema.tables WHERE table_schema = " + projectId + " AND table_type = 'BASE TABLE'",
                String.class);
    }

    public void createTable(CreateTableRequest request) {
        StringBuilder query = new StringBuilder("CREATE TABLE ").append(request.getProjectId()).append(".").append(request.getTitle()).append(" (");
        request.getColumns().forEach(c -> query.append("kolon veritipi,"));
        query.append(")");
        jdbcTemplate.execute(query.toString());
    }

}
