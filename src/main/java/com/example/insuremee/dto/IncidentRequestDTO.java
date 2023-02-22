package com.example.insuremee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IncidentRequestDTO {

    private Long id;
    private String description;
    private String code;
    private String status;
    private Integer amount;
    private LocalDateTime reportedDate;
    private String reportedBy;

}
