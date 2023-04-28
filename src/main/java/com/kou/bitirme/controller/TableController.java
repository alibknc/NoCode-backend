package com.kou.bitirme.controller;

import com.kou.bitirme.dto.request.CreateTableRequest;
import com.kou.bitirme.dto.response.TableDto;
import com.kou.bitirme.service.TableService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/tables")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService){
        this.tableService = tableService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllTables(String projectId) {
        return ResponseEntity.ok(tableService.getTables(projectId));
    }

    @PostMapping
    public ResponseEntity<Void> createTable(@Valid @RequestBody CreateTableRequest table) {
        tableService.createTable(table);
        return ResponseEntity.ok().build();
    }

}
