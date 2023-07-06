package com.kou.bitirme.controller;

import com.kou.bitirme.dto.request.CreateEndpointRequest;
import com.kou.bitirme.dto.response.EndpointDto;
import com.kou.bitirme.service.EndpointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/endpoint")
public class EndpointController {

    private final EndpointService endpointService;

    public EndpointController(EndpointService endpointService) {
        this.endpointService = endpointService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createEndpoint(@Valid @RequestBody CreateEndpointRequest request) {
        endpointService.createEndpoint(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<EndpointDto>> getAllEndpoints(@RequestParam UUID projectId) {
        return ResponseEntity.ok(endpointService.getAllEndpoints(projectId));
    }

}
