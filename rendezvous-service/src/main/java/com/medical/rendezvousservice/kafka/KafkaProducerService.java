package com.medical.rendezvousservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.medical.rendezvousservice.model.AppointmentBookedEvent;
import com.medical.rendezvousservice.model.ConsultationCompletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final String SEPARATOR = "======================";
    private static final String PRODUCER_HEADER = "=== KAFKA PRODUCER ===";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new JavaTimeModule());

    public void sendAppointmentBookedEvent(AppointmentBookedEvent event) {
        sendEvent("appointment-booked", event);
    }

    public void sendConsultationCompletedEvent(ConsultationCompletedEvent event) {
        sendEvent("consultation-completed", event);
    }

    /**
     * Méthode générique d'envoi d'événements Kafka
     */
    private void sendEvent(String topic, Object event) {
        try {
            String message = objectMapper.writeValueAsString(event);

            log.info(PRODUCER_HEADER);
            log.info("Topic: {}", topic);
            log.info("Message: {}", message);

            kafkaTemplate.send(topic, message);

            log.info("Événement envoyé avec succès !");
            log.info(SEPARATOR);

        } catch (Exception e) {
            log.error("Erreur lors de l'envoi de l'événement Kafka [{}]: {}", topic, e.getMessage(), e);
        }
    }
}
