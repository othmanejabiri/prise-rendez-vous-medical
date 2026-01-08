CREATE TABLE medical_record (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                record_date DATE NOT NULL,
                                diagnosis VARCHAR(500),
                                treatment VARCHAR(500),
                                notes TEXT,
                                doctor_id BIGINT NOT NULL,
                                patient_id BIGINT NOT NULL,
                                FOREIGN KEY (patient_id) REFERENCES patient(id)
);