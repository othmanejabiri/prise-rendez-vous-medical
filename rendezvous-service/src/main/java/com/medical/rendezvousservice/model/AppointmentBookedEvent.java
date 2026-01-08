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
public class AppointmentBookedEvent {
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private String patientEmail;
    private String doctorName;
    private LocalDateTime appointmentDate;
    private String reason;
}