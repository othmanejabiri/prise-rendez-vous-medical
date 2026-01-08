package com.medical.notificationservice.service;

import com.medical.notificationservice.kafka.KafkaProducer;
import com.medical.notificationservice.model.AppointmentBookedEvent;
import com.medical.notificationservice.model.ConsultationCompletedEvent;
import com.medical.notificationservice.model.ReminderSentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final KafkaProducer kafkaProducer;

    public void sendAppointmentConfirmation(AppointmentBookedEvent event) {
        log.info("📧 Envoi de la confirmation de RDV au patient #{}...", event.getPatientId());
        log.info("   Email: {}", event.getPatientEmail());
        log.info("   Docteur: {}", event.getDoctorName());
        log.info("   Date: {}", event.getAppointmentDate());
        log.info("   Raison: {}", event.getReason());

        // Simulation d'envoi d'email
        simulateEmailSending(event.getPatientEmail(),
                "Confirmation de rendez-vous",
                "Votre rendez-vous avec le Dr. " + event.getDoctorName() + " est confirmé.");

        // Envoyer un événement "reminder-sent" après confirmation
        sendReminder(event);
    }

    public void handleConsultationCompleted(ConsultationCompletedEvent event) {
        log.info("📋 Traitement de la consultation terminée #{}...", event.getConsultationId());
        log.info("   Patient: #{}", event.getPatientId());
        log.info("   Diagnostic: {}", event.getDiagnosis());
        log.info("   Notes: {}", event.getNotes());

        // Simulation d'envoi de résumé au patient
        log.info("📧 Envoi du résumé de consultation au patient #{}...", event.getPatientId());
    }

    private void sendReminder(AppointmentBookedEvent event) {
        // Créer un événement de rappel
        ReminderSentEvent reminderEvent = new ReminderSentEvent(
                event.getAppointmentId(),
                event.getPatientId(),
                event.getPatientEmail(),
                LocalDateTime.now(),
                "N'oubliez pas votre rendez-vous demain !"
        );

        // Envoyer l'événement dans Kafka
        kafkaProducer.sendReminderSent(reminderEvent);

        log.info("⏰ Rappel programmé pour le RDV #{}", event.getAppointmentId());
    }

    private void simulateEmailSending(String email, String subject, String body) {
        log.info("📨 [SIMULATION EMAIL]");
        log.info("   À: {}", email);
        log.info("   Sujet: {}", subject);
        log.info("   Corps: {}", body);
        log.info("   Email envoyé avec succès !");
    }
}