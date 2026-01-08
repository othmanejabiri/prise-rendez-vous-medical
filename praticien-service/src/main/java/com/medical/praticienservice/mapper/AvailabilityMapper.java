package com.medical.praticienservice.mapper;

import com.medical.praticienservice.dto.AvailabilityDTO;
import com.medical.praticienservice.entity.Availability;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AvailabilityMapper {

    // Entité → DTO
    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "doctor.firstName", target = "doctorFirstName")
    @Mapping(source = "doctor.lastName", target = "doctorLastName")
    AvailabilityDTO toDTO(Availability availability);

    // DTO → Entité
    @Mapping(source = "doctorId", target = "doctor.id")
    @Mapping(target = "doctor.firstName", ignore = true)
    @Mapping(target = "doctor.lastName", ignore = true)
    @Mapping(target = "doctor.email", ignore = true)
    @Mapping(target = "doctor.phone", ignore = true)
    @Mapping(target = "doctor.specialty", ignore = true)
    Availability toEntity(AvailabilityDTO dto);

    // Liste Entités → Liste DTOs
    List<AvailabilityDTO> toDTOList(List<Availability> availabilities);
}
