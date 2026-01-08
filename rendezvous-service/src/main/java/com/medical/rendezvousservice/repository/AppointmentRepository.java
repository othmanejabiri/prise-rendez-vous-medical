package com.medical.rendezvousservice.repository;

import com.medical.rendezvousservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // 🔍 Recherche par patient
    List<Appointment> findByPatientId(Long patientId);

    // 🔍 Recherche par docteur
    List<Appointment> findByDoctorId(Long doctorId);

    // 🔍 Recherche par statut
    List<Appointment> findByStatus(String status);

    // 🔍 Pour le batch : récupérer les RDV d'une spécialité sur une période donnée
    List<Appointment> findBySpecialtyIdAndDateBetween(Long specialtyId, LocalDate start, LocalDate end);

    // 🔍 Pour le batch : compter les RDV annulés par spécialité
    long countBySpecialtyIdAndStatus(Long specialtyId, String status);
}
