package com.medical.rendezvousservice.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class MonthlyStatsController {

    private final MonthlySpecialtyStatsService statsService;

    @PostMapping("/generate")
    public String manualGenerate() {
        statsService.generateMonthlyStats();
        return "Batch exécuté !";
    }

    @GetMapping("/monthly")
    public List<MonthlySpecialtyStats> getMonthlyStats(@RequestParam String month) {
        return statsService.getStatsForMonth(LocalDate.parse(month));
    }
}
