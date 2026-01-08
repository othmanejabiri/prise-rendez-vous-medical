package com.medical.rendezvousservice.mapper;

import com.medical.rendezvousservice.dto.ConsultationDTO;
import com.medical.rendezvousservice.entity.Consultation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConsultationMapper {

    // Entité → DTO
    @Mapping(source = "appointment.id", target = "appointmentId")
    ConsultationDTO toDTO(Consultation consultation);

    // DTO → Entité
    @Mapping(source = "appointmentId", target = "appointment.id")
    @Mapping(target = "appointment.date", ignore = true)
    @Mapping(target = "appointment.startTime", ignore = true)
    @Mapping(target = "appointment.endTime", ignore = true)
    @Mapping(target = "appointment.status", ignore = true)
    @Mapping(target = "appointment.reason", ignore = true)
    @Mapping(target = "appointment.patientId", ignore = true)
    @Mapping(target = "appointment.doctorId", ignore = true)
    Consultation toEntity(ConsultationDTO dto);

    // Liste Entités → Liste DTOs
    List<ConsultationDTO> toDTOList(List<Consultation> consultations);
}