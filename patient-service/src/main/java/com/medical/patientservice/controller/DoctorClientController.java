package com.medical.patientservice.controller;

import com.medical.patientservice.client.DoctorDTO;
import com.medical.patientservice.client.PraticienServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients/doctors")
@RequiredArgsConstructor
public class DoctorClientController {

    private static final Logger logger =
            LoggerFactory.getLogger(DoctorClientController.class);

    private final PraticienServiceClient praticienServiceClient;

    @GetMapping
    @CircuitBreaker(name = "praticienService", fallbackMethod = "getAllDoctorsFallback")
    public List<DoctorDTO> getAllDoctors() {
        return praticienServiceClient.getAllDoctors();
    }

    public List<DoctorDTO> getAllDoctorsFallback(Exception e) {
        logger.error(
                "Circuit Breaker activé - Praticien Service DOWN: {}",
                e.getMessage(),
                e
        );
        return List.of(); // Liste vide en cas de panne
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "praticienService", fallbackMethod = "getDoctorByIdFallback")
    public Object getDoctorById(@PathVariable Long id) {
        return praticienServiceClient.getDoctorById(id);
    }

    public Object getDoctorByIdFallback(Long id, Exception e) {

        logger.warn(
                "Fallback déclenché pour getDoctorById - id={} - exception={}",
                id,
                e.getMessage(),
                e
        );

        Map<String, Object> response = new HashMap<>();
        response.put("error", "Praticien Service indisponible");
        response.put("message", "Impossible de récupérer le médecin avec l'ID " + id);
        response.put("doctorId", id);

        return response;
    }

}
