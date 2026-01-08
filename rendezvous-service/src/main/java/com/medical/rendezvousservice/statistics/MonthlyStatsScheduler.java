package com.medical.rendezvousservice.statistics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MonthlyStatsScheduler {

    private final MonthlySpecialtyStatsService statsService;

    @Scheduled(cron = "0 0 2 1 * *")  // le 1er jour de chaque mois à 02:00
    public void runBatch() {
        log.info("🚀 BATCH MENSUEL LANCÉ AUTOMATIQUEMENT");
        statsService.generateMonthlyStats();
    }
}
