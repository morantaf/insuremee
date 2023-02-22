package com.example.insuremee.controller;

import com.example.insuremee.dto.IncidentRequestDTO;
import com.example.insuremee.service.IncidentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/incident")
@AllArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping(path = "/report")
    public void report(@RequestBody IncidentRequestDTO request) {
        incidentService.report(request);
    }
}
