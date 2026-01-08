package com.medical.praticienservice.controller;

import com.medical.praticienservice.dto.AvailabilityDTO;
import com.medical.praticienservice.entity.Availability;
import com.medical.praticienservice.mapper.AvailabilityMapper;
import com.medical.praticienservice.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/praticiens/availabilities")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;
    private final AvailabilityMapper availabilityMapper;

    @GetMapping
    public ResponseEntity<List<AvailabilityDTO>> getAllAvailabilities() {
        List<Availability> availabilities = availabilityService.getAllAvailabilities();
        return ResponseEntity.ok(availabilityMapper.toDTOList(availabilities));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AvailabilityDTO>> getAvailabilitiesByDoctor(@PathVariable Long doctorId) {
        List<Availability> availabilities = availabilityService.getAvailabilitiesByDoctorId(doctorId);
        return ResponseEntity.ok(availabilityMapper.toDTOList(availabilities));
    }

    @GetMapping("/doctor/{doctorId}/available")
    public ResponseEntity<List<AvailabilityDTO>> getAvailableAvailabilitiesByDoctor(@PathVariable Long doctorId) {
        List<Availability> availabilities = availabilityService.getAvailableAvailabilitiesByDoctorId(doctorId);
        return ResponseEntity.ok(availabilityMapper.toDTOList(availabilities));
    }

    @PostMapping
    public ResponseEntity<AvailabilityDTO> createAvailability(@RequestBody AvailabilityDTO availabilityDTO) {
        Availability availability = availabilityMapper.toEntity(availabilityDTO);
        Availability savedAvailability = availabilityService.saveAvailability(availability);
        return ResponseEntity.ok(availabilityMapper.toDTO(savedAvailability));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id) {
        availabilityService.deleteAvailability(id);
        return ResponseEntity.noContent().build();
    }
}