package com.example.insuremee.domains;

import com.example.insuremee.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "incident_requests")
@Table
@NoArgsConstructor
public class IncidentRequest {

    @Id
    @SequenceGenerator(name = "incident_request_sequence", allocationSize = 1, sequenceName = "incident_request_sequence")
    @GeneratedValue(generator = "incident_request_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String description;

    @Column
    private String code;

    @Column
    private LocalDateTime reportedDate;

    @Enumerated(EnumType.STRING)
    @Column
    private RequestStatus status;

    @Column
    private Integer amount;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User reportedBy;

    public IncidentRequest(String description, User reportedBy) {
        this.code = UUID.randomUUID().toString();
        this.reportedDate = LocalDateTime.now();
        this.description = description;
        this.reportedBy = reportedBy;
    }

    public IncidentRequest(String description, String code, LocalDateTime reportedDate) {
        this.description = description;
        this.code = code;
        this.reportedDate = reportedDate;
        this.status = RequestStatus.Reported;
    }

    public IncidentRequest(String description, String code, LocalDateTime reportedDate, RequestStatus status, Integer amount) {
        this.description = description;
        this.code = code;
        this.reportedDate = reportedDate;
        this.status = status;
        this.amount = amount;
    }
}
