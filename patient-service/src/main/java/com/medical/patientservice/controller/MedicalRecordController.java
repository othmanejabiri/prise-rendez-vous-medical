package com.medical.patientservice.controller;

import com.medical.patientservice.dto.MedicalRecordDTO;
import com.medical.patientservice.entity.MedicalRecord;
import com.medical.patientservice.mapper.MedicalRecordMapper;
import com.medical.patientservice.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients/records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;
    private final MedicalRecordMapper medicalRecordMapper;

    @GetMapping
    public ResponseEntity<List<MedicalRecordDTO>> getAllMedicalRecords() {
        List<MedicalRecord> medicalRecords =
                medicalRecordService.getAllMedicalRecords();
        return ResponseEntity.ok(
                medicalRecordMapper.toDTOList(medicalRecords)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordDTO> getMedicalRecordById(
            @PathVariable Long id
    ) {
        MedicalRecord medicalRecord =
                medicalRecordService.getMedicalRecordById(id);
        return ResponseEntity.ok(
                medicalRecordMapper.toDTO(medicalRecord)
        );
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecordDTO>> getMedicalRecordsByPatient(
            @PathVariable Long patientId
    ) {
        List<MedicalRecord> medicalRecords =
                medicalRecordService.getMedicalRecordsByPatientId(patientId);
        return ResponseEntity.ok(
                medicalRecordMapper.toDTOList(medicalRecords)
        );
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<MedicalRecordDTO>> getMedicalRecordsByDoctor(
            @PathVariable Long doctorId
    ) {
        List<MedicalRecord> medicalRecords =
                medicalRecordService.getMedicalRecordsByDoctorId(doctorId);
        return ResponseEntity.ok(
                medicalRecordMapper.toDTOList(medicalRecords)
        );
    }

    @PostMapping
    public ResponseEntity<MedicalRecordDTO> createMedicalRecord(
            @RequestBody MedicalRecordDTO medicalRecordDTO
    ) {
        MedicalRecord medicalRecord =
                medicalRecordMapper.toEntity(medicalRecordDTO);
        MedicalRecord savedMedicalRecord =
                medicalRecordService.saveMedicalRecord(medicalRecord);

        return ResponseEntity.ok(
                medicalRecordMapper.toDTO(savedMedicalRecord)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(
            @PathVariable Long id
    ) {
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.noContent().build();
    }
}
