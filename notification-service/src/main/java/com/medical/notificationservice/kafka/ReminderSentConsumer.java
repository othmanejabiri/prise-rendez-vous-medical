package com.medical.notificationservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.notificationservice.model.ReminderSentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReminderSentConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "reminder-sent", groupId = "notification-service-group")
    public void consume(String message) {
        try {
            ReminderSentEvent event = objectMapper.readValue(message, ReminderSentEvent.class);

            log.info("📩 === ReminderSent EVENT REÇU ===");
            log.info("RDV ID          : {}", event.getAppointmentId());
            log.info("Patient ID      : {}", event.getPatientId());
            log.info("Email patient   : {}", event.getPatientEmail());
            log.info("Envoyé à        : {}", event.getReminderSentAt());
            log.info("Message         : {}", event.getMessage());
            log.info("===================================");

        } catch (Exception e) {
            log.error(" Erreur lors de la désérialisation de ReminderSent: {}", e.getMessage());
        }
    }
}
