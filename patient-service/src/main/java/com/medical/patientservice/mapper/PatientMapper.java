package com.medical.patientservice.mapper;

import com.medical.patientservice.dto.PatientDTO;
import com.medical.patientservice.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {

    // Entité → DTO
    PatientDTO toDTO(Patient patient);

    // DTO → Entité
    Patient toEntity(PatientDTO dto);

    // Liste Entités → Liste DTOs
    List<PatientDTO> toDTOList(List<Patient> patients);
}