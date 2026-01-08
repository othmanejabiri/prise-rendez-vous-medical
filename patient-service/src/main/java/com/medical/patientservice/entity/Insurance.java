package com.medical.patientservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String providerName;

    private String policyNumber;

    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}