package com.medical.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentBookedEvent {
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private String patientEmail;
    private String doctorName;
    private LocalDateTime appointmentDate;
    private String reason;
}