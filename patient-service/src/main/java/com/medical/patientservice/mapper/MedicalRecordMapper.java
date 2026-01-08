package com.medical.patientservice.mapper;

import com.medical.patientservice.dto.MedicalRecordDTO;
import com.medical.patientservice.entity.MedicalRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicalRecordMapper {

    // Entity → DTO
    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "patient.firstName", target = "patientFirstName")
    @Mapping(source = "patient.lastName", target = "patientLastName")
    @Mapping(source = "doctorId", target = "doctorId")
    MedicalRecordDTO toDTO(MedicalRecord medicalRecord);


    // DTO → Entity
    @Mapping(target = "patient.id", source = "patientId")
    @Mapping(target = "patient.firstName", ignore = true)
    @Mapping(target = "patient.lastName", ignore = true)
    @Mapping(target = "patient.email", ignore = true)
    @Mapping(target = "patient.phone", ignore = true)
    @Mapping(target = "patient.dateOfBirth", ignore = true)
    @Mapping(target = "patient.address", ignore = true)
    @Mapping(target = "doctorId", source = "doctorId")
    MedicalRecord toEntity(MedicalRecordDTO dto);


    List<MedicalRecordDTO> toDTOList(List<MedicalRecord> medicalRecords);
}
