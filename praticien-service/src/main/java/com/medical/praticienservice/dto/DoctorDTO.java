package com.medical.praticienservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    // Au lieu de renvoyer l'objet Specialty complet , on renvoie juste l'info nécessaire
    private Long specialtyId;
    private String specialtyName;
}