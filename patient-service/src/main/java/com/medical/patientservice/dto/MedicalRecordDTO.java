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
public class MedicalRecordDTO {
    private Long id;
    private LocalDate recordDate;
    private String diagnosis;
    private String treatment;
    private String notes;

    // Infos du patient
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;

    // ID du docteur
    private Long doctorId;
}