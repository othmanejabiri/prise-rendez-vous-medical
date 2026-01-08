package com.medical.rendezvousservice.controller;

import com.medical.rendezvousservice.entity.Consultation;
import com.medical.rendezvousservice.entity.Prescription;
import com.medical.rendezvousservice.service.ConsultationService;
import com.medical.rendezvousservice.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rendezvous/prescriptions")
@RequiredArgsConstructor
@Slf4j
public class PrescriptionController {

    private static final String ERROR_CREATION_PRESCRIPTION =
            "Erreur lors de la création de la prescription";

    private final PrescriptionService prescriptionService;
    private final ConsultationService consultationService;

    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
    }

    @GetMapping("/consultation/{consultationId}")
    public ResponseEntity<List<Prescription>> getPrescriptionsByConsultation(
            @PathVariable Long consultationId) {
        return ResponseEntity.ok(
                prescriptionService.getPrescriptionsByConsultationId(consultationId)
        );
    }

    @PostMapping
    public ResponseEntity<Prescription> createPrescription(
            @RequestBody Map<String, Object> prescriptionData) {

        try {
            Prescription prescription = new Prescription();
            prescription.setMedication((String) prescriptionData.get("medication"));
            prescription.setDosage((String) prescriptionData.get("dosage"));
            prescription.setFrequency((String) prescriptionData.get("frequency"));

            Integer duration = Integer.valueOf(
                    prescriptionData.get("duration").toString()
            );
            prescription.setDuration(duration);

            prescription.setPrescriptionDate(
                    LocalDate.parse((String) prescriptionData.get("prescriptionDate"))
            );
            prescription.setInstructions((String) prescriptionData.get("instructions"));

            Map<String, Object> consultationMap =
                    (Map<String, Object>) prescriptionData.get("consultation");

            Long consultationId =
                    Long.valueOf(consultationMap.get("id").toString());

            Consultation consultation =
                    consultationService.getConsultationById(consultationId);

            prescription.setConsultation(consultation);

            Prescription savedPrescription =
                    prescriptionService.savePrescription(prescription);

            log.info("Prescription sauvegardée avec succès, id={}",
                    savedPrescription.getId());

            return ResponseEntity.ok(savedPrescription);

        } catch (IllegalArgumentException | NullPointerException ex) {
            log.error(ERROR_CREATION_PRESCRIPTION, ex);
            throw new IllegalStateException(ERROR_CREATION_PRESCRIPTION);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        log.info("Prescription supprimée, id={}", id);
        return ResponseEntity.noContent().build();
    }
}
