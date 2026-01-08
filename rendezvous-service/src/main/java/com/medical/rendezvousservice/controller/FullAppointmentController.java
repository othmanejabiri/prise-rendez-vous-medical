package com.medical.rendezvousservice.controller;

import com.medical.rendezvousservice.client.DoctorDTO;
import com.medical.rendezvousservice.client.PatientDTO;
import com.medical.rendezvousservice.client.PatientServiceClient;
import com.medical.rendezvousservice.client.PraticienServiceClient;
import com.medical.rendezvousservice.entity.Appointment;
import com.medical.rendezvousservice.service.AppointmentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rendezvous/full")
@RequiredArgsConstructor
public class FullAppointmentController {

    private static final Logger logger =
            LoggerFactory.getLogger(FullAppointmentController.class);

    //  Constantes pour éviter la duplication (Sonar S1192)
    private static final String KEY_ERROR = "error";
    private static final String KEY_MESSAGE = "message";

    private final AppointmentService appointmentService;
    private final PatientServiceClient patientServiceClient;
    private final PraticienServiceClient praticienServiceClient;

    @GetMapping("/appointment/{id}")
    @CircuitBreaker(name = "patientService", fallbackMethod = "getAppointmentFallback")
    public Map<String, Object> getFullAppointment(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        Appointment appointment = appointmentService.getAppointmentById(id);

        PatientDTO patient =
                patientServiceClient.getPatientById(appointment.getPatientId());

        DoctorDTO doctor =
                praticienServiceClient.getDoctorById(appointment.getDoctorId());

        response.put("appointment", appointment);
        response.put("patient", patient);
        response.put("doctor", doctor);

        return response;
    }

    //  Fallback Circuit Breaker
    public Map<String, Object> getAppointmentFallback(Long id, Exception e) {

        logger.error(
                "Fallback getFullAppointment déclenché (appointmentId={}) : {}",
                id,
                e.getMessage(),
                e
        );

        Map<String, Object> response = new HashMap<>();
        response.put(KEY_ERROR, "Service temporairement indisponible");
        response.put(
                KEY_MESSAGE,
                "Un ou plusieurs services sont DOWN. Réessayez plus tard."
        );
        response.put("appointmentId", id);

        return response;
    }

    @GetMapping("/doctors")
    @CircuitBreaker(name = "praticienService", fallbackMethod = "getDoctorsFallback")
    public Object getAllDoctors() {
        return praticienServiceClient.getAllDoctors();
    }

    public Object getDoctorsFallback(Exception e) {

        logger.warn(
                "Fallback getAllDoctors déclenché : {}",
                e.getMessage(),
                e
        );

        Map<String, String> response = new HashMap<>();
        response.put(KEY_ERROR, "Praticien Service indisponible");
        response.put(KEY_MESSAGE, "Le service Praticien est DOWN. Réessayez plus tard.");

        return response;
    }

    @GetMapping("/patients")
    @CircuitBreaker(name = "patientService", fallbackMethod = "getPatientsFallback")
    public Object getAllPatients() {
        return patientServiceClient.getAllPatients();
    }

    public Object getPatientsFallback(Exception e) {

        logger.warn(
                "Fallback getAllPatients déclenché : {}",
                e.getMessage(),
                e
        );

        Map<String, String> response = new HashMap<>();
        response.put(KEY_ERROR, "Patient Service indisponible");
        response.put(KEY_MESSAGE, "Le service Patient est DOWN. Réessayez plus tard.");

        return response;
    }
}
