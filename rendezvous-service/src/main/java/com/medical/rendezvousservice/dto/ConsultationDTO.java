package com.medical.rendezvousservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultationDTO {
    private Long id;
    private LocalDateTime consultationDate;
    private String notes;
    private String diagnosis;
    private Double fee;

    // Info du rendez-vous (juste l'ID)
    private Long appointmentId;
}