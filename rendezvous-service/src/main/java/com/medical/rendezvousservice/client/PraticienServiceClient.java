package com.medical.rendezvousservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "praticien-service")
public interface PraticienServiceClient {

    @GetMapping("/praticiens/doctors")
    List<DoctorDTO> getAllDoctors();

    @GetMapping("/praticiens/doctors/{id}")
    DoctorDTO getDoctorById(@PathVariable("id") Long id);
}