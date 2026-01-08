package com.medical.patientservice.mapper;

import com.medical.patientservice.dto.InsuranceDTO;
import com.medical.patientservice.entity.Insurance;
import com.medical.patientservice.entity.Patient;
import org.mapstruct.*;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InsuranceMapper {

    // DTO → Entity (MapStruct remplira insurance, et AfterMapping créera un patient minimal)
    @Mapping(source = "patientId", target = "patient.id")
    Insurance toEntity(InsuranceDTO dto);

    // Entity → DTO
    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "patient.firstName", target = "patientFirstName")
    @Mapping(source = "patient.lastName", target = "patientLastName")
    InsuranceDTO toDTO(Insurance insurance);

    List<InsuranceDTO> toDTOList(List<Insurance> insurances);

    // Fix MapStruct: si patient == null, on crée un patient minimal avec juste l'id
    @AfterMapping
    default void setPatient(@MappingTarget Insurance insurance, InsuranceDTO dto) {
        if (dto.getPatientId() != null) {
            Patient p = new Patient();
            p.setId(dto.getPatientId());
            insurance.setPatient(p);
        }
    }
}
