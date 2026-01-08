package com.medical.praticienservice.mapper;

import com.medical.praticienservice.dto.DoctorDTO;
import com.medical.praticienservice.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DoctorMapper {

    // Entité → DTO
    @Mapping(source = "specialty.id", target = "specialtyId")
    @Mapping(source = "specialty.name", target = "specialtyName")
    DoctorDTO toDTO(Doctor doctor);

    // DTO → Entité
    @Mapping(source = "specialtyId", target = "specialty.id")
    @Mapping(target = "specialty.name", ignore = true)
    @Mapping(target = "specialty.description", ignore = true)
    Doctor toEntity(DoctorDTO dto);

    // Liste Entités → Liste DTOs
    List<DoctorDTO> toDTOList(List<Doctor> doctors);
}