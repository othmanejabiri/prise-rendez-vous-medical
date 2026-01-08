CREATE TABLE monthly_specialty_stats (
                                         id BIGINT PRIMARY KEY AUTO_INCREMENT,

                                         specialty_id BIGINT NOT NULL,
                                         specialty_name VARCHAR(255),

                                         month_value DATE NOT NULL,

                                         total_appointments INT DEFAULT 0,
                                         total_consultations INT DEFAULT 0,
                                         total_revenue DOUBLE DEFAULT 0,
                                         average_duration DOUBLE DEFAULT 0,
                                         canceled_appointments INT DEFAULT 0,
                                         patient_count INT DEFAULT 0,

                                         generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
