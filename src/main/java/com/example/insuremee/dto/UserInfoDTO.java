package com.example.insuremee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private InsuranceInfoDTO insurance;
    private Set<RoleInfoDTO> roles;
    private Set<IncidentRequestDTO> requests;

    public UserInfoDTO(Long id, String email, String firstName, String lastName, Set<RoleInfoDTO> roleInfoDTOS) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roleInfoDTOS;
    }

}
