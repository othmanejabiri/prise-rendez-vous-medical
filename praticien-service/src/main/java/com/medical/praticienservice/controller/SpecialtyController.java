package com.medical.praticienservice.controller;

import com.medical.praticienservice.dto.SpecialtyDTO;
import com.medical.praticienservice.entity.Specialty;
import com.medical.praticienservice.mapper.SpecialtyMapper;
import com.medical.praticienservice.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/praticiens/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService specialtyService;
    private final SpecialtyMapper specialtyMapper;  // Injection du Mapper

    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> getAllSpecialties() {
        List<Specialty> specialties = specialtyService.getAllSpecialties();
        return ResponseEntity.ok(specialtyMapper.toDTOList(specialties));  // Conversion en DTO
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> getSpecialtyById(@PathVariable Long id) {
        Specialty specialty = specialtyService.getSpecialtyById(id);
        return ResponseEntity.ok(specialtyMapper.toDTO(specialty));  // Conversion en DTO
    }

    @PostMapping
    public ResponseEntity<SpecialtyDTO> createSpecialty(@RequestBody SpecialtyDTO specialtyDTO) {
        Specialty specialty = specialtyMapper.toEntity(specialtyDTO);  // DTO → Entité
        Specialty savedSpecialty = specialtyService.saveSpecialty(specialty);
        return ResponseEntity.ok(specialtyMapper.toDTO(savedSpecialty));  // Entité → DTO
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialty(@PathVariable Long id) {
        specialtyService.deleteSpecialty(id);
        return ResponseEntity.noContent().build();
    }
}