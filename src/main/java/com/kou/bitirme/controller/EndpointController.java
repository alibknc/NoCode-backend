package com.kou.bitirme.controller;

import com.kou.bitirme.service.EndpointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EndpointController {

    private final EndpointService endpointService;

    public EndpointController(EndpointService endpointService) {
        this.endpointService = endpointService;
    }

    @GetMapping("/**")
    public ResponseEntity<Object> callEndpoint(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String key = request.getParameter("key");

        if (key.isEmpty()) {
            throw new RuntimeException("API key must not empty");
        } else {
            String endpoint = uri.replace("/api/", "");
            return ResponseEntity.ok(endpointService.runQuery(endpoint, key));
        }
    }

}
