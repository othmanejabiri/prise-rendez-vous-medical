package com.medical.rendezvousservice.mapper;

import com.medical.rendezvousservice.dto.AppointmentDTO;
import com.medical.rendezvousservice.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppointmentMapper {

    // Entité → DTO
    AppointmentDTO toDTO(Appointment appointment);

    // DTO → Entité
    Appointment toEntity(AppointmentDTO dto);

    // Liste Entités → Liste DTOs
    List<AppointmentDTO> toDTOList(List<Appointment> appointments);
}