package com.medical.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationCompletedEvent {
    private Long consultationId;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime consultationDate;
    private String diagnosis;
    private String notes;
}