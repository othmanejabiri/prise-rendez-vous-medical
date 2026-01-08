package com.medical.rendezvousservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private String status; // PENDING, CONFIRMED, CANCELLED, COMPLETED

    private String reason;

    private Long patientId;  // Reference au Patient du patient-service

    private Long doctorId;// Reference au Doctor du praticien-service

    private Long specialtyId;

}