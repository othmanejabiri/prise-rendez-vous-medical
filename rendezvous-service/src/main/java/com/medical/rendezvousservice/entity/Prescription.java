package com.medical.rendezvousservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medication;

    private String dosage;

    private String frequency;

    private Integer duration; // en jours

    private LocalDate prescriptionDate;

    private String instructions;

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;
}