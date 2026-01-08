package com.medical.rendezvousservice.mapper;

import com.medical.rendezvousservice.dto.PrescriptionDTO;
import com.medical.rendezvousservice.entity.Prescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PrescriptionMapper {

    // Entité → DTO
    @Mapping(source = "consultation.id", target = "consultationId")
    PrescriptionDTO toDTO(Prescription prescription);

    // DTO → Entité
    @Mapping(source = "consultationId", target = "consultation.id")
    @Mapping(target = "consultation.consultationDate", ignore = true)
    @Mapping(target = "consultation.notes", ignore = true)
    @Mapping(target = "consultation.diagnosis", ignore = true)
    @Mapping(target = "consultation.fee", ignore = true)
    @Mapping(target = "consultation.appointment", ignore = true)
    Prescription toEntity(PrescriptionDTO dto);

    // Liste Entités → Liste DTOs
    List<PrescriptionDTO> toDTOList(List<Prescription> prescriptions);
}