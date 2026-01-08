package com.medical.notificationservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.medical.notificationservice.model.AppointmentBookedEvent;
import com.medical.notificationservice.model.ConsultationCompletedEvent;
import com.medical.notificationservice.model.ReminderSentEvent;

@RestController
@RequestMapping("/kafka-test")
@RequiredArgsConstructor
public class KafkaTestController {

    private final KafkaProducer producer;

    // 1️⃣ Tester l'envoi d'un rendez-vous
    @PostMapping("/appointment-booked")
    public ResponseEntity<String> sendAppointment(@RequestBody AppointmentBookedEvent event) {
        producer.sendAppointmentBooked(event);
        return ResponseEntity.ok("AppointmentBooked event sent!");
    }

    // 2️⃣ Tester l'envoi d'une consultation terminée
    @PostMapping("/consultation-completed")
    public ResponseEntity<String> sendConsultation(@RequestBody ConsultationCompletedEvent event) {
        producer.sendConsultationCompleted(event);
        return ResponseEntity.ok("ConsultationCompleted event sent!");
    }

    // 3️⃣ Tester l'envoi d'un rappel de rdv
    @PostMapping("/reminder-sent")
    public ResponseEntity<String> sendReminder(@RequestBody ReminderSentEvent event) {
        producer.sendReminderSent(event);
        return ResponseEntity.ok("ReminderSent event sent!");
    }
}
