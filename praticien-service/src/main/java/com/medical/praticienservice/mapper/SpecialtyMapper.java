package com.medical.praticienservice.mapper;

import com.medical.praticienservice.dto.SpecialtyDTO;
import com.medical.praticienservice.entity.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecialtyMapper {

    // Entité → DTO
    SpecialtyDTO toDTO(Specialty specialty);

    // DTO → Entité
    Specialty toEntity(SpecialtyDTO dto);

    // Liste Entités → Liste DTOs
    List<SpecialtyDTO> toDTOList(List<Specialty> specialties);
}