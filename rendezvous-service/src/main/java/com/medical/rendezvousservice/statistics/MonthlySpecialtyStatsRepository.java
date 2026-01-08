package com.medical.rendezvousservice.statistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MonthlySpecialtyStatsRepository extends JpaRepository<MonthlySpecialtyStats, Long> {

    // Le nom doit matcher EXACTEMENT l'entité
    List<MonthlySpecialtyStats> findByMonthValue(LocalDate monthValue);

    boolean existsBySpecialtyIdAndMonthValue(Long specialtyId, LocalDate monthValue);
}
