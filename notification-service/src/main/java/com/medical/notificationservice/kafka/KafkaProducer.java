package com.medical.notificationservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.medical.notificationservice.model.AppointmentBookedEvent;
import com.medical.notificationservice.model.ConsultationCompletedEvent;
import com.medical.notificationservice.model.ReminderSentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String APPOINTMENT_TOPIC = "appointment-booked";
    private static final String CONSULTATION_TOPIC = "consultation-completed";
    private static final String REMINDER_TOPIC = "reminder-sent";

    //  Constructeur manuel pour configurer proprement ObjectMapper
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;

        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    private void sendJson(String topic, Object event) {
        try {
            String json = objectMapper.writeValueAsString(event);

            log.info("=== KAFKA PRODUCER ===");
            log.info("Topic: {}", topic);
            log.info("Message envoyé: {}", json);

            kafkaTemplate.send(topic, json);

            log.info("======================");

        } catch (Exception e) {
            log.error("Erreur d'envoi Kafka: {}", e.getMessage());
        }
    }

    public void sendAppointmentBooked(AppointmentBookedEvent event) {
        sendJson(APPOINTMENT_TOPIC, event);
    }

    public void sendConsultationCompleted(ConsultationCompletedEvent event) {
        sendJson(CONSULTATION_TOPIC, event);
    }

    public void sendReminderSent(ReminderSentEvent event) {
        sendJson(REMINDER_TOPIC, event);
    }
}
