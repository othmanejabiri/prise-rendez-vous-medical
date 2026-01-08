package com.medical.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReminderSentEvent {
    private Long appointmentId;
    private Long patientId;
    private String patientEmail;
    private LocalDateTime reminderSentAt;
    private String message;
}