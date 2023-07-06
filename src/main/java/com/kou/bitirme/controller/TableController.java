package com.kou.bitirme.controller;

import com.kou.bitirme.dto.request.CreateTableRequest;
import com.kou.bitirme.service.TableService;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/tables")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTables(@RequestParam UUID id) {
        Map<String, Object> result = tableService.getTables(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<Void> createTable(@Valid @RequestBody CreateTableRequest table) {
        tableService.createTable(table);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/content")
    public ResponseEntity<Map<String, Object>> getTableByName(@RequestParam UUID projectId, @RequestParam String tableName) {
        Map<String, Object> result = tableService.getTableByName(projectId, tableName);
        return ResponseEntity.ok().body(result);
    }

}
