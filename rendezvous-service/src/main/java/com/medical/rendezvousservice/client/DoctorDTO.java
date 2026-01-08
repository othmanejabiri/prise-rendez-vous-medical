package com.medical.rendezvousservice.client;

import lombok.Data;

@Data
public class DoctorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}