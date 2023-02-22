package com.example.insuremee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InsuranceInfoDTO {

    private Long id;
    private String name;
    private Integer amount;
    private Integer price;
}
