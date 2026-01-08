package com.medical.rendezvousservice.controller;

import com.medical.rendezvousservice.entity.Appointment;
import com.medical.rendezvousservice.entity.Consultation;
import com.medical.rendezvousservice.kafka.KafkaProducerService;
import com.medical.rendezvousservice.model.ConsultationCompletedEvent;
import com.medical.rendezvousservice.service.AppointmentService;
import com.medical.rendezvousservice.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rendezvous/consultations")
@RequiredArgsConstructor
public class ConsultationController {

    private static final Logger logger =
            LoggerFactory.getLogger(ConsultationController.class);

    private final ConsultationService consultationService;
    private final AppointmentService appointmentService;
    private final KafkaProducerService kafkaProducerService;

    @GetMapping
    public ResponseEntity<List<Consultation>> getAllConsultations() {
        return ResponseEntity.ok(consultationService.getAllConsultations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getConsultationById(@PathVariable Long id) {
        return ResponseEntity.ok(consultationService.getConsultationById(id));
    }

    @PostMapping
    public ResponseEntity<Consultation> createConsultation(
            @RequestBody Map<String, Object> consultationData) {

        try {
            Consultation consultation = new Consultation();

            consultation.setConsultationDate(
                    LocalDateTime.parse((String) consultationData.get("consultationDate"))
            );
            consultation.setNotes((String) consultationData.get("notes"));
            consultation.setDiagnosis((String) consultationData.get("diagnosis"));

            Double fee = Double.valueOf(consultationData.get("fee").toString());
            consultation.setFee(fee);

            Map<String, Object> appointmentMap =
                    (Map<String, Object>) consultationData.get("appointment");

            Long appointmentId =
                    Long.valueOf(appointmentMap.get("id").toString());

            Appointment appointment =
                    appointmentService.getAppointmentById(appointmentId);

            consultation.setAppointment(appointment);

            Consultation savedConsultation =
                    consultationService.saveConsultation(consultation);

            logger.info(
                    "Consultation sauvegardée avec succès (id={})",
                    savedConsultation.getId()
            );

            ConsultationCompletedEvent event =
                    ConsultationCompletedEvent.builder()
                            .consultationId(savedConsultation.getId())
                            .patientId(appointment.getPatientId())
                            .doctorId(appointment.getDoctorId())
                            .consultationDate(savedConsultation.getConsultationDate())
                            .diagnosis(savedConsultation.getDiagnosis())
                            .notes(savedConsultation.getNotes())
                            .build();

            kafkaProducerService.sendConsultationCompletedEvent(event);

            return ResponseEntity.ok(savedConsultation);

        } catch (IllegalArgumentException e) {
            logger.error("Erreur de format des données consultation", e);
            throw new IllegalArgumentException("Données de consultation invalides");
        } catch (IllegalStateException e) {
            logger.error("Erreur métier lors de la création de la consultation", e);
            throw new IllegalStateException("Impossible de créer la consultation");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);
        return ResponseEntity.noContent().build();
    }
}
