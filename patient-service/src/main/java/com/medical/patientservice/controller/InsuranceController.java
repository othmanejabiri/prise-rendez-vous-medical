package com.medical.patientservice.controller;

import com.medical.patientservice.dto.InsuranceDTO;
import com.medical.patientservice.entity.Insurance;
import com.medical.patientservice.mapper.InsuranceMapper;
import com.medical.patientservice.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients/insurances")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;
    private final InsuranceMapper insuranceMapper;

    @GetMapping
    public ResponseEntity<List<InsuranceDTO>> getAllInsurances() {
        List<Insurance> insurances = insuranceService.getAllInsurances();
        return ResponseEntity.ok(insuranceMapper.toDTOList(insurances));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceDTO> getInsuranceById(@PathVariable Long id) {
        Insurance insurance = insuranceService.getInsuranceById(id);
        return ResponseEntity.ok(insuranceMapper.toDTO(insurance));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<InsuranceDTO>> getInsurancesByPatient(@PathVariable Long patientId) {
        List<Insurance> insurances = insuranceService.getInsurancesByPatientId(patientId);
        return ResponseEntity.ok(insuranceMapper.toDTOList(insurances));
    }

    @PostMapping
    public ResponseEntity<InsuranceDTO> createInsurance(@RequestBody InsuranceDTO insuranceDTO) {
        Insurance insurance = insuranceMapper.toEntity(insuranceDTO);
        Insurance savedInsurance = insuranceService.saveInsurance(insurance);
        return ResponseEntity.ok(insuranceMapper.toDTO(savedInsurance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsurance(@PathVariable Long id) {
        insuranceService.deleteInsurance(id);
        return ResponseEntity.noContent().build();
    }
}