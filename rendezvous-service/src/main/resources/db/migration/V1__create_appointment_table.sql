CREATE TABLE appointment (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             date DATE NOT NULL,
                             start_time TIME NOT NULL,
                             end_time TIME NOT NULL,
                             status VARCHAR(50) NOT NULL,
                             reason VARCHAR(255),
                             patient_id BIGINT NOT NULL,
                             doctor_id BIGINT NOT NULL
);