package com.medical.praticienservice.controller;

import com.medical.praticienservice.dto.DoctorDTO;
import com.medical.praticienservice.entity.Doctor;
import com.medical.praticienservice.mapper.DoctorMapper;
import com.medical.praticienservice.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/praticiens/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;  // Injection du Mapper

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctorMapper.toDTOList(doctors));  // Conversion en DTO
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctorMapper.toDTO(doctor));  // Conversion en DTO
    }

    @GetMapping("/specialty/{specialtyId}")
    public ResponseEntity<List<DoctorDTO>> getDoctorsBySpecialty(@PathVariable Long specialtyId) {
        List<Doctor> doctors = doctorService.getDoctorsBySpecialtyId(specialtyId);
        return ResponseEntity.ok(doctorMapper.toDTOList(doctors));  // Conversion en DTO
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.toEntity(doctorDTO);  // DTO → Entité
        Doctor savedDoctor = doctorService.saveDoctor(doctor);
        return ResponseEntity.ok(doctorMapper.toDTO(savedDoctor));  // Entité → DTO
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}