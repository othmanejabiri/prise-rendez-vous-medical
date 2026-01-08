package com.medical.notificationservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.notificationservice.model.ConsultationCompletedEvent;
import com.medical.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsultationCompletedConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "consultation-completed", groupId = "notification-service-group")
    public void consumeConsultationCompleted(String message) {
        try {
            log.info("=== KAFKA CONSUMER ===");
            log.info("Topic: consultation-completed");
            log.info("Message reçu: {}", message);

            ConsultationCompletedEvent event = objectMapper.readValue(message, ConsultationCompletedEvent.class);

            // Traiter la consultation terminée
            notificationService.handleConsultationCompleted(event);

            log.info("Consultation #{} traitée avec succès", event.getConsultationId());
            log.info("======================");
        } catch (Exception e) {
            log.error("Erreur lors du traitement du message: {}", e.getMessage());
        }
    }
}