package com.medical.rendezvousservice.statistics;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlySpecialtyStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long specialtyId;
    private String specialtyName;

    // ⚠️ IMPORTANT : DOIT S’APPELER monthValue
    @Column(name = "month_value")
    private LocalDate monthValue;

    private Integer totalAppointments;
    private Integer totalConsultations;
    private Double totalRevenue;
    private Double averageDuration;
    private Integer canceledAppointments;
    private Integer patientCount;

    private LocalDateTime generatedAt;
}
