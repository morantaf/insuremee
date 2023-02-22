package com.example.insuremee.domains;

import com.example.insuremee.enums.SubscriptionPaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "subscription_fees")
@Table
@NoArgsConstructor
public class SubscriptionFee {

    @Id
    @SequenceGenerator(name = "subscription_sequence", allocationSize = 1, sequenceName = "subscription_sequence")
    @GeneratedValue(generator = "subscription_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private Integer amount;

    @ManyToOne
    private User user;

    @Column
    private LocalDateTime creationDate;

    @Column
    private LocalDateTime paidDate;

    @Column
    @Enumerated(EnumType.STRING)
    private SubscriptionPaymentStatus status;

    public SubscriptionFee(Integer amount, User user, LocalDateTime creationDate, SubscriptionPaymentStatus status) {
        this.amount = amount;
        this.user = user;
        this.creationDate = creationDate;
        this.status = status;
    }

    public SubscriptionFee(Integer amount, User user, LocalDateTime creationDate, LocalDateTime paidDate) {
        this.amount = amount;
        this.user = user;
        this.creationDate = creationDate;
        this.paidDate = paidDate;
        this.status = SubscriptionPaymentStatus.Paid;
    }

    public SubscriptionFee(Integer amount, User user, LocalDateTime creationDate) {
        this.amount = amount;
        this.user = user;
        this.creationDate = creationDate;
        this.status = SubscriptionPaymentStatus.Incoming;
    }
}
