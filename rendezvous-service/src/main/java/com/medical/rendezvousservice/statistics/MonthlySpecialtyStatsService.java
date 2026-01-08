package com.medical.rendezvousservice.statistics;

import com.medical.rendezvousservice.entity.Appointment;
import com.medical.rendezvousservice.entity.Consultation;
import com.medical.rendezvousservice.repository.AppointmentRepository;
import com.medical.rendezvousservice.repository.ConsultationRepository;
import com.medical.rendezvousservice.statistics.SpecialtyClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MonthlySpecialtyStatsService {

    private final AppointmentRepository appointmentRepository;
    private final ConsultationRepository consultationRepository;
    private final MonthlySpecialtyStatsRepository statsRepository;
    private final SpecialtyClient specialtyClient;

    /**
     * ⭐ Batch automatique : 1er jour du mois à 00:00
     */
    @Scheduled(cron = "0 0 0 1 * *")
    public void runMonthlyBatch() {
        log.info("🚀 Batch mensuel : génération des statistiques...");
        generateMonthlyStats();
        log.info("✅ Batch terminé");
    }

    /**
     * ⭐ Exécution manuelle via Postman
     */
    public List<MonthlySpecialtyStats> generateMonthlyStats() {

        YearMonth currentMonth = YearMonth.now().minusMonths(1);
        LocalDate start = currentMonth.atDay(1);
        LocalDate end = currentMonth.atEndOfMonth();

        log.info("📊 Génération des stats du mois : {}", currentMonth);

        List<Long> specialtyIds = appointmentRepository.findAll()
                .stream()
                .map(Appointment::getSpecialtyId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        List<MonthlySpecialtyStats> results = new ArrayList<>();

        for (Long specialtyId : specialtyIds) {

            // Nom de spécialité via Feign
            String specialtyName = "Inconnue";
            try {
                specialtyName = specialtyClient.getSpecialty(specialtyId).getName();
            } catch (Exception e) {
                log.warn("⚠ Spécialité {} introuvable dans le praticien-service", specialtyId);
            }

            // RDV du mois
            List<Appointment> appointments = appointmentRepository
                    .findBySpecialtyIdAndDateBetween(specialtyId, start, end);

            long totalAppointments = appointments.size();
            long canceledAppointments = appointmentRepository
                    .countBySpecialtyIdAndStatus(specialtyId, "CANCELLED");

            // Consultations du mois
            List<Consultation> consultations = consultationRepository
                    .findByAppointment_SpecialtyIdAndConsultationDateBetween(
                            specialtyId,
                            start.atStartOfDay(),
                            end.atTime(23, 59)
                    );

            long totalConsultations = consultations.size();
            double totalRevenue = consultations.stream()
                    .mapToDouble(c -> c.getFee() != null ? c.getFee() : 0)
                    .sum();

            double averageDuration = appointments.stream()
                    .filter(a -> a.getStartTime() != null && a.getEndTime() != null)
                    .mapToDouble(a -> Duration.between(a.getStartTime(), a.getEndTime()).toMinutes())
                    .average()
                    .orElse(0);

            MonthlySpecialtyStats stats = MonthlySpecialtyStats.builder()
                    .specialtyId(specialtyId)
                    .specialtyName(specialtyName)
                    .monthValue(start)
                    .totalAppointments((int) totalAppointments)
                    .totalConsultations((int) totalConsultations)
                    .totalRevenue(totalRevenue)
                    .averageDuration(averageDuration)
                    .canceledAppointments((int) canceledAppointments)
                    .patientCount(0) // Optionnel pour plus tard
                    .generatedAt(LocalDateTime.now())
                    .build();

            statsRepository.save(stats);
            results.add(stats);

            log.info("📈 Stats générées pour spécialité {} ({})", specialtyId, specialtyName);
        }

        return results;
    }

    /**
     * ⭐ Récupération des statistiques pour un mois donné
     */
    public List<MonthlySpecialtyStats> getStatsForMonth(LocalDate month) {
        return statsRepository.findByMonthValue(month);
    }

}
