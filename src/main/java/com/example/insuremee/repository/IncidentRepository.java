package com.example.insuremee.repository;

import com.example.insuremee.domains.IncidentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository  extends JpaRepository<IncidentRequest, Long> {
}
