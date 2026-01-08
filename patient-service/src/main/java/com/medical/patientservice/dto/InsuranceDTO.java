package com.medical.patientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceDTO {
    private Long id;
    private String providerName;
    private String policyNumber;
    private LocalDate expirationDate;

    // Infos du patient
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
}