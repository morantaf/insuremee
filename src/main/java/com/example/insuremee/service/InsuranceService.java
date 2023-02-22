package com.example.insuremee.service;

import com.example.insuremee.domains.Insurance;
import com.example.insuremee.domains.User;
import com.example.insuremee.dto.InsuranceInfoDTO;
import com.example.insuremee.enums.UserRole;
import com.example.insuremee.exception.DuplicateEntityException;
import com.example.insuremee.exception.NotFoundException;
import com.example.insuremee.repository.InsuranceRepository;
import com.example.insuremee.repository.UserRepository;
import com.example.insuremee.security.SecurityService;
import com.example.insuremee.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final Mapper mapper;
    private final SecurityService securityService;
    private final UserRepository userRepository;

    public List<Insurance> getAll() {
        return  insuranceRepository.findAll();
    }

    public Insurance get(Long id) throws NotFoundException {
        Optional<Insurance> optionalInsurance = insuranceRepository.findById(id);
        if(optionalInsurance.isEmpty()) {
            throw new NotFoundException("No assurance found");
        }
        return optionalInsurance.get();
    }

    public List<InsuranceInfoDTO> renderAll() {
        List<Insurance> insurances = getAll();
        return insurances.stream().map(mapper::insuranceToDTO).toList();
    }

    public InsuranceInfoDTO render(Long id) throws NotFoundException {
        Insurance insurance = get(id);
        return mapper.insuranceToDTO(insurance);
    }

    public void subscribe(Long id) throws DuplicateEntityException, NotFoundException {
        User user = securityService.getCurrentUser();
        Insurance insurance = get(id);
        if(Objects.equals(insurance.getId(),user.getInsurance().getId())) {
            throw new DuplicateEntityException("User already subscribed to insurance");
        }
        user.setInsurance(insurance);
        userRepository.save(user);
    }
}
