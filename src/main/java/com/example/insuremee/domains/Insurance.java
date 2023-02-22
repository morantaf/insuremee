package com.example.insuremee.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity( name = "insurance")
@Table
@NoArgsConstructor
public class Insurance {

    @Id
    @SequenceGenerator(name = "insurance_sequence", allocationSize = 1, sequenceName = "insurance_sequence")
    @GeneratedValue(generator = "insurance_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer amount;

    @Column
    private Integer price;

    public Insurance(String name, Integer amount, Integer price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

}
