package com.example.insuremee.service;

import com.example.insuremee.domains.IncidentRequest;
import com.example.insuremee.domains.Role;
import com.example.insuremee.domains.User;
import com.example.insuremee.dto.IncidentRequestDTO;
import com.example.insuremee.repository.IncidentRepository;
import com.example.insuremee.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class IncidentService {

    private IncidentRepository incidentRepository;
    private SecurityService securityService;

    public void report(IncidentRequestDTO request) {
        User currentUser = securityService.getCurrentUser();
        String description = request.getDescription();
        IncidentRequest incidentRequest = new IncidentRequest(description, currentUser);
        incidentRepository.save(incidentRequest);

    }
}
