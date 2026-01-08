package com.medical.rendezvousservice.controller;

import com.medical.rendezvousservice.entity.Appointment;
import com.medical.rendezvousservice.kafka.KafkaProducerService;
import com.medical.rendezvousservice.model.AppointmentBookedEvent;
import com.medical.rendezvousservice.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rendezvous/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private static final Logger logger =
            LoggerFactory.getLogger(AppointmentController.class);

    private final AppointmentService appointmentService;
    private final KafkaProducerService kafkaProducerService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(
            @PathVariable Long patientId) {
        return ResponseEntity.ok(
                appointmentService.getAppointmentsByPatientId(patientId)
        );
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(
            @PathVariable Long doctorId) {
        return ResponseEntity.ok(
                appointmentService.getAppointmentsByDoctorId(doctorId)
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Appointment>> getAppointmentsByStatus(
            @PathVariable String status) {
        return ResponseEntity.ok(
                appointmentService.getAppointmentsByStatus(status)
        );
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(
            @RequestBody Map<String, Object> appointmentData) {

        try {
            Appointment appointment = new Appointment();

            // Champs simples
            appointment.setDate(
                    LocalDate.parse((String) appointmentData.get("date"))
            );
            appointment.setStartTime(
                    LocalTime.parse((String) appointmentData.get("startTime"))
            );
            appointment.setEndTime(
                    LocalTime.parse((String) appointmentData.get("endTime"))
            );
            appointment.setStatus((String) appointmentData.get("status"));
            appointment.setReason((String) appointmentData.get("reason"));

            // Conversions sécurisées
            appointment.setPatientId(
                    convertToLong(appointmentData.get("patientId"))
            );
            appointment.setDoctorId(
                    convertToLong(appointmentData.get("doctorId"))
            );
            appointment.setSpecialtyId(
                    convertToLong(appointmentData.get("specialtyId"))
            );

            Appointment savedAppointment =
                    appointmentService.saveAppointment(appointment);

            logger.info(
                    "RDV sauvegardé avec succès - id={}",
                    savedAppointment.getId()
            );

            AppointmentBookedEvent event =
                    AppointmentBookedEvent.builder()
                            .appointmentId(savedAppointment.getId())
                            .patientId(savedAppointment.getPatientId())
                            .doctorId(savedAppointment.getDoctorId())
                            .patientEmail(
                                    "patient" + savedAppointment.getPatientId()
                                            + "@medical.com"
                            )
                            .doctorName(
                                    "Docteur #" + savedAppointment.getDoctorId()
                            )
                            .appointmentDate(
                                    LocalDateTime.of(
                                            savedAppointment.getDate(),
                                            savedAppointment.getStartTime()
                                    )
                            )
                            .reason(savedAppointment.getReason())
                            .build();

            kafkaProducerService.sendAppointmentBookedEvent(event);

            return ResponseEntity.ok(savedAppointment);

        } catch (IllegalArgumentException e) {
            //  Conforme Sonar : on RELANCE avec contexte (sans logger ici)
            throw new IllegalArgumentException(
                    "Erreur lors de la création du rendez-vous : données invalides",
                    e
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Conversion sûre vers Long (Sonar-friendly).
     */
    private Long convertToLong(Object value) {
        if (value instanceof Integer intValue) {
            return intValue.longValue();
        }
        if (value instanceof Long longValue) {
            return longValue;
        }
        throw new IllegalArgumentException(
                "Type invalide pour conversion Long: " + value
        );
    }
}
