package com.medical.rendezvousservice.repository;

import com.medical.rendezvousservice.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    @Query("""
        SELECT c FROM Consultation c
        WHERE c.consultationDate BETWEEN :start AND :end
    """)
    List<Consultation> findConsultationsInPeriod(LocalDateTime start, LocalDateTime end);
    List<Consultation> findByAppointment_SpecialtyIdAndConsultationDateBetween(
            Long specialtyId, LocalDateTime start, LocalDateTime end);

}
