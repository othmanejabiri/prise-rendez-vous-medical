package com.medical.patientservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate recordDate;

    private String diagnosis;

    private String treatment;

    private String notes;

    private Long doctorId;  // Reference au Doctor du praticien-service

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}