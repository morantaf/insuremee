package com.example.insuremee.controller;

import com.example.insuremee.dto.InsuranceInfoDTO;
import com.example.insuremee.exception.DuplicateEntityException;
import com.example.insuremee.exception.NotFoundException;
import com.example.insuremee.service.InsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/insurances")
@AllArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    @GetMapping(path = "/")
    public List<InsuranceInfoDTO> getAll() {
        return insuranceService.renderAll();
    }

    @GetMapping(path = "/{id}")
    public InsuranceInfoDTO get(@PathVariable Long id) throws NotFoundException {
        return insuranceService.render(id);
    }

    @PostMapping(path = "/{id}/subscribe")
    public void subscribe(@PathVariable Long id) throws NotFoundException, DuplicateEntityException {
        insuranceService.subscribe(id);
    }

}
