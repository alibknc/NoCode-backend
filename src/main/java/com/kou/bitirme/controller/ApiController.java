package com.kou.bitirme.controller;

import com.kou.bitirme.service.EndpointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ApiController {

    private final EndpointService endpointService;

    public ApiController(EndpointService endpointService) {
        this.endpointService = endpointService;
    }

    @GetMapping("/**")
    public ResponseEntity<Object> callGetEndpoint(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String key = request.getParameter("key");

        if (key.isEmpty()) {
            throw new RuntimeException("API key must not empty");
        } else {
            String endpoint = uri.replace("/api/", "");
            return ResponseEntity.ok(endpointService.runQuery(endpoint, key, null, request.getMethod()));
        }
    }

    @PostMapping("/**")
    public ResponseEntity<Object> callPostEndpoint(HttpServletRequest request, @RequestBody Map<String, Object> body) {
        String uri = request.getRequestURI();
        String key = request.getParameter("key");

        if (key.isEmpty()) {
            throw new RuntimeException("API key must not empty");
        } else {
            String endpoint = uri.replace("/api/", "");
            return ResponseEntity.ok(endpointService.runQuery(endpoint, key, body, request.getMethod()));
        }
    }

}
