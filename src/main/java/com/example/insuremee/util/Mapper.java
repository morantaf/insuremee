package com.example.insuremee.util;

import com.example.insuremee.domains.IncidentRequest;
import com.example.insuremee.domains.Insurance;
import com.example.insuremee.domains.Role;
import com.example.insuremee.domains.User;
import com.example.insuremee.dto.IncidentRequestDTO;
import com.example.insuremee.dto.InsuranceInfoDTO;
import com.example.insuremee.dto.RoleInfoDTO;
import com.example.insuremee.dto.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    public InsuranceInfoDTO insuranceToDTO(Insurance insurance) {
        ModelMapper modelMapper = getModelMapper();
        return modelMapper.map(insurance, InsuranceInfoDTO.class);
    }

    public UserInfoDTO userToDTO(User user) {
        ModelMapper modelMapper = getModelMapper();
        TypeMap<User, UserInfoDTO> typeMap = modelMapper.createTypeMap(User.class,UserInfoDTO.class);
        typeMap.addMapping(User::getUserRoles, UserInfoDTO::setRoles);
        return modelMapper.map(user, UserInfoDTO.class);
    }

    public RoleInfoDTO roleToDTO(Role role) {
        ModelMapper modelMapper = getModelMapper();
        return modelMapper.map(role, RoleInfoDTO.class);
    }

    public IncidentRequestDTO IncidentRequestToDTO(IncidentRequest request) {
        ModelMapper modelMapper = getModelMapper();
        TypeMap<IncidentRequest, IncidentRequestDTO> typeMap = modelMapper.createTypeMap(IncidentRequest.class,IncidentRequestDTO.class);
        typeMap.addMappings(mapper -> mapper.map(src -> src.getReportedBy().getFullName(), IncidentRequestDTO::setReportedBy));
        return modelMapper.map(request, IncidentRequestDTO.class);
    }

    private ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
