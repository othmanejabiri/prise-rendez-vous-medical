package com.medical.notificationservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.notificationservice.model.AppointmentBookedEvent;
import com.medical.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppointmentBookedConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "appointment-booked", groupId = "notification-service-group")
    public void consumeAppointmentBooked(String message) {
        try {
            log.info("=== KAFKA CONSUMER ===");
            log.info("Topic: appointment-booked");
            log.info("Message reçu: {}", message);

            AppointmentBookedEvent event = objectMapper.readValue(message, AppointmentBookedEvent.class);

            // Envoyer la notification
            notificationService.sendAppointmentConfirmation(event);

            log.info("Notification envoyée avec succès pour le RDV #{}", event.getAppointmentId());
            log.info("======================");
        } catch (Exception e) {
            log.error("Erreur lors du traitement du message: {}", e.getMessage());
        }
    }
}