package com.medical.rendezvousservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime consultationDate;

    private String notes;

    private String diagnosis;

    private Double fee;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}