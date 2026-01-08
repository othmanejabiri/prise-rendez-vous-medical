package com.medical.rendezvousservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultationCompletedEvent {
    private Long consultationId;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime consultationDate;
    private String diagnosis;
    private String notes;
}