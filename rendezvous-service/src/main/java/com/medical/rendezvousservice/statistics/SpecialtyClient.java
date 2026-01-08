package com.medical.rendezvousservice.statistics;

import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "praticien-service-specialty", url = "http://localhost:9001")
public interface SpecialtyClient {

    @GetMapping("/praticiens/specialties/{id}")
    SpecialtyResponse getSpecialty(@PathVariable("id") Long id);

    @Data
    class SpecialtyResponse {
        private Long id;
        private String name;
        private String description;
    }
}
