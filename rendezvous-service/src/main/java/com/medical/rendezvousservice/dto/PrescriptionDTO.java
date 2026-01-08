package com.medical.rendezvousservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionDTO {
    private Long id;
    private String medication;
    private String dosage;
    private String frequency;
    private Integer duration;
    private LocalDate prescriptionDate;
    private String instructions;

    // Info de la consultation
    private Long consultationId;
}